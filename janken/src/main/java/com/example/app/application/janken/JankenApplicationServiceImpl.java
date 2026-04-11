package com.example.app.application.janken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.application.room.RoomService;
import com.example.app.application.room.dto.RoomRegisterDto;
import com.example.app.domain.janken.model.JankenChoiceRecord;
import com.example.app.domain.janken.model.JankenGameEngine;
import com.example.app.domain.janken.model.JankenMode;
import com.example.app.domain.janken.model.JankenPlayerResultRecord;
import com.example.app.domain.janken.model.RoundResult;
import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.domain.user.vo.UserId;
import com.example.app.infrastructure.jankenchoice.entity.JankenChoiceEntity;
import com.example.app.infrastructure.jankenchoice.jpa.JankenChoiceJpaRepository;
import com.example.app.infrastructure.jankenplayerresult.entity.JankenPlayerResultEntity;
import com.example.app.infrastructure.jankenplayerresult.jpa.JankenPlayerResultJpaRepository;
import com.example.app.infrastructure.jankenroundresult.entity.JankenRoundResultEntity;
import com.example.app.infrastructure.jankenroundresult.jpa.JankenRoundResultJpaRepository;
import com.example.app.infrastructure.roomplayer.repository.RoomPlayerJpaRepository;
import com.example.app.presentation.janken.JankenChoiceForm;

import lombok.RequiredArgsConstructor;

/**
 * じゃんけんゲームのサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class JankenApplicationServiceImpl implements JankenApplicationService {

    private final AuditorAware<Integer> auditorAware;
    private final RoomService roomService;
    private final JankenGameEngine jankenGameEngine;
    private final RoomPlayerJpaRepository roomPlayerJpaRepository;
    private final JankenRoundResultJpaRepository jankenRoundResultJpaRepository;
    private final JankenPlayerResultJpaRepository jankenPlayerResultJpaRepository;
    private final JankenChoiceJpaRepository jankenChoiceJpaRepository;

    @Override
    public List<JankenChoiceRecord> getJankenChoices(RoomId roomId, UserId userId) {

        List<JankenChoiceEntity> entities = jankenChoiceJpaRepository
                .findByRoomIdAndPlayerId(roomId.value(), userId.value());
        return entities.stream()
                .map(e -> new JankenChoiceRecord(
                        new RoomId(e.getRoomId()),
                        new OrderNo(e.getOrderNo()),
                        new PlayerId(e.getPlayerId()),
                        e.getJankenHand()))
                .toList();
    }

    @Override
    @Transactional
    public void registerJankenChoices(RoomId roomId, JankenChoiceForm form) {

        // ログインユーザー は AuditorAware で取得 
        Integer currentUserId = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new IllegalStateException("ログインユーザーが取得できません"));

        // プレイヤーの出し手を洗い替え
        jankenChoiceJpaRepository.deleteByRoomIdAndPlayerId(roomId.value(), currentUserId);

        // form -> Entity を生成
        List<JankenChoiceEntity> choices = form.getChoices().stream()
                .map(row -> new JankenChoiceEntity(
                        roomId.value(),
                        currentUserId,
                        row.getOrderNo(),
                        row.getHand()))
                .toList();

        jankenChoiceJpaRepository.saveAll(choices);

    }

    @Override
    public void battle(RoomId roomId) {
        // ルーム情報を取得
        RoomRegisterDto room = roomService.findById(roomId);

        // JankenMode を取得
        JankenMode mode = (JankenMode) room.getGameMode();

        // ルームに登録されている全プレイヤーID取得
        Set<PlayerId> allPlayers = roomPlayerJpaRepository.findPlayerIdByRoomId(roomId.value())
                .stream()
                .map(PlayerId::new)
                .collect(Collectors.toSet());

        // 全プレイヤーの出し手情報取得
        List<JankenChoiceRecord> choices = jankenChoiceJpaRepository.findByRoomId(roomId.value())
                .stream()
                .map(e -> new JankenChoiceRecord(
                        new RoomId(e.getRoomId()),
                        new OrderNo(e.getOrderNo()),
                        new PlayerId(e.getPlayerId()),
                        e.getJankenHand()))
                .toList();

        // maxRounds を取得
        int maxRounds = room.getRoundCount();

        // 判定処理はJankenGameEngine に任せる
        Map<OrderNo, RoundResult> results = jankenGameEngine.judge(
                mode,
                choices,
                allPlayers,
                maxRounds);

        // ラウンドごとの判定結果をDB保存
        saveRoundResults(roomId, results);

        // プレイヤーの成績を集計
        List<JankenPlayerResultRecord> playerResults = calculatePlayerResults(roomId, mode, results,
                allPlayers);

        // プレイヤーの成績を保存
        savePlayerResults(roomId, playerResults);
    }

    /** 
     * ラウンドごとの対戦結果を集計し、プレイヤーごとの成績を返す。
     * 
     *  @param mode じゃんけんモード
     *  @param result ラウンドごとの対戦結果
     *  @param allPlayers 全プレイヤーID
     *  @author takeshi.kashiwagi
     */
    private List<JankenPlayerResultRecord> calculatePlayerResults(
            RoomId roomId, JankenMode mode,
            Map<OrderNo, RoundResult> results, Set<PlayerId> allPlayers) {

        // --- 1. 勝ち数・負け数の集計 ---
        Map<PlayerId, Integer> winCount = new HashMap<>(); // プレイヤーID → 勝利数
        Map<PlayerId, Integer> loseCount = new HashMap<>(); // プレイヤーID → 敗北数
        allPlayers.forEach(p -> {
            winCount.put(p, 0);
            loseCount.put(p, 0);
        });

        for (RoundResult result : results.values()) {
            if (result.isDraw())
                continue;
            result.getWinners().forEach(w -> winCount.put(w, winCount.get(w) + 1));
            result.getLosers().forEach(l -> loseCount.put(l, loseCount.get(l) + 1));
        }

        // --- 2. ゲームモーごとのルールでソートする。 ---
        List<PlayerId> sortedPlayers = allPlayers.stream()
                .sorted((p1, p2) -> {
                    Score s1 = scoreOf(mode, winCount.get(p1), loseCount.get(p1));
                    Score s2 = scoreOf(mode, winCount.get(p2), loseCount.get(p2));

                    int cmp = Integer.compare(s2.primary(), s1.primary());
                    if (cmp != 0)
                        return cmp;

                    return Integer.compare(s2.secondary(), s1.secondary());
                })
                .toList();

        // --- 3. 順位を付けながら JankenPlayerResultRecord を生成 ---
        List<JankenPlayerResultRecord> playerResults = new ArrayList<>();

        int rank = 1;
        int index = 0;
        PlayerId prevPlayer = null;

        // 同着の判定。同じ戦績（勝利数と敗北数が一致）の場合は、同着とみなす。
        for (PlayerId player : sortedPlayers) {

            if (prevPlayer != null) {
                boolean sameRank = scoreOf(mode, winCount.get(player), loseCount.get(player))
                        .equals(scoreOf(mode, winCount.get(prevPlayer), loseCount.get(prevPlayer)));

                if (!sameRank) {
                    rank = index + 1;
                }
            }

            playerResults.add(new JankenPlayerResultRecord(
                    roomId,
                    player,
                    winCount.get(player),
                    loseCount.get(player),
                    rank));

            prevPlayer = player;
            index++;
        }

        return playerResults;

    }

    /**
     * プレイヤーの順位付けに使用する比較用スコア。
     *
     * <p>primary と secondary の 2 軸で比較を行う。
     * ゲームモードごとに scoreOf() で生成され、
     * ソートおよび同着判定の両方で利用される。
     *
     * <ul>
     *   <li>WINNER_STAYS：primary = 勝利数（降順）</li>
     *   <li>LOSER_STAYS：primary = -敗北数（昇順）</li>
     *   <li>TOTAL_BATTLE：primary = 勝利数（降順）、secondary = -敗北数（昇順）</li>
     * </ul>
     */
    private record Score(int primary, int secondary) {
    }

    /**
     * 指定されたゲームモードに基づき、順位付けに使用する Score を生成する。
     *
     * @param mode ゲームモード
     * @param win  プレイヤーの勝利数
     * @param lose プレイヤーの敗北数
     * @return 比較用の Score（primary / secondary の 2 軸）
     */
    private Score scoreOf(
            JankenMode mode,
            int win,
            int lose) {
        return switch (mode) {
            case WINNER_STAYS -> new Score(win, 0); // 勝ち数だけで比較

            case LOSER_STAYS -> new Score(-lose, 0); // 負け数が少ないほど上位 → 符号反転

            case TOTAL_BATTLE -> new Score(win, -lose); // 勝ち数降順 → 負け数昇順
        };
    }

    /**
     * janken_round_resultテーブルの洗替
     */
    private void saveRoundResults(RoomId roomId, Map<OrderNo, RoundResult> results) {
        // 1. 洗い替えのため削除
        jankenRoundResultJpaRepository.deleteByRoomId(roomId.value());

        // 2. RoundResult → Entity へ直接変換
        List<JankenRoundResultEntity> entities = results.entrySet().stream()
                .map(entry -> {
                    OrderNo orderNo = entry.getKey();
                    RoundResult round = entry.getValue();

                    List<Integer> winnerIds = round.getWinners().stream()
                            .map(PlayerId::value)
                            .toList();

                    List<Integer> loserIds = round.getLosers().stream()
                            .map(PlayerId::value)
                            .toList();

                    return new JankenRoundResultEntity(
                            roomId.value(),
                            orderNo.value(),
                            round.isDraw(),
                            winnerIds,
                            loserIds);
                })
                .toList();

        // 3. 一括保存
        jankenRoundResultJpaRepository.saveAll(entities);

    }

    /**
     * janken_player_resultテーブルの洗替
     */
    private void savePlayerResults(RoomId roomId, List<JankenPlayerResultRecord> playerResults) {
        // 1. 洗い替えのため削除
        jankenPlayerResultJpaRepository.deleteByRoomId(roomId.value());

        // 2. Record → Entity 変換
        List<JankenPlayerResultEntity> entities = playerResults.stream()
                .map(r -> new JankenPlayerResultEntity(
                        r.roomId().value(),
                        r.playerId().value(),
                        r.winCount(),
                        r.loseCount(),
                        r.finalRank()))
                .toList();

        // 3. 一括保存
        jankenPlayerResultJpaRepository.saveAll(entities);

    }

}
