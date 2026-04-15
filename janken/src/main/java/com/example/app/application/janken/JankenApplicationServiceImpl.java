package com.example.app.application.janken;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.application.janken.dto.BattleResultDto;
import com.example.app.application.janken.dto.JankenRegisteredStatusDto;
import com.example.app.application.janken.dto.PlayerResultView;
import com.example.app.application.janken.dto.PlayerStatusDto;
import com.example.app.application.janken.dto.RoundHandView;
import com.example.app.application.janken.dto.RoundResultView;
import com.example.app.application.room.RoomService;
import com.example.app.application.room.dto.RoomRegisterDto;
import com.example.app.application.user.UserService;
import com.example.app.domain.janken.model.JankenChoiceRecord;
import com.example.app.domain.janken.model.JankenMode;
import com.example.app.domain.janken.model.JankenPlayerResultRecord;
import com.example.app.domain.janken.model.RoundResult;
import com.example.app.domain.janken.service.JankenGameEngine;
import com.example.app.domain.janken.service.JankenResultService;
import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.jankenchoice.entity.JankenChoiceEntity;
import com.example.app.infrastructure.jankenchoice.jpa.JankenChoiceJpaRepository;
import com.example.app.infrastructure.jankenplayerresult.entity.JankenPlayerResultEntity;
import com.example.app.infrastructure.jankenplayerresult.jpa.JankenPlayerResultJpaRepository;
import com.example.app.infrastructure.jankenroundresult.entity.JankenRoundResultEntity;
import com.example.app.infrastructure.jankenroundresult.jpa.JankenRoundResultJpaRepository;
import com.example.app.infrastructure.roomplayer.repository.RoomPlayerJpaRepository;
import com.example.app.infrastructure.user.jpa.UserJpaRepository;
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
    private final JankenResultService jankenResultService;
    private final RoomPlayerJpaRepository roomPlayerJpaRepository;
    private final JankenRoundResultJpaRepository jankenRoundResultJpaRepository;
    private final JankenPlayerResultJpaRepository jankenPlayerResultJpaRepository;
    private final JankenChoiceJpaRepository jankenChoiceJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserService userService;

    @Override
    public List<JankenChoiceRecord> getJankenChoices(Integer roomIdValue, Integer userIdValue) {

        List<JankenChoiceEntity> entities = jankenChoiceJpaRepository
                .findByRoomIdAndPlayerId(roomIdValue, userIdValue);
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
    public void registerJankenChoices(Integer roomIdValue, JankenChoiceForm form) {

        // ログインユーザー は AuditorAware で取得 
        Integer currentUserId = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new IllegalStateException("ログインユーザーが取得できません"));

        // プレイヤーの出し手を洗い替え
        jankenChoiceJpaRepository.deleteByRoomIdAndPlayerId(roomIdValue, currentUserId);

        // form -> Entity を生成
        List<JankenChoiceEntity> choices = form.getChoices().stream()
                .map(row -> new JankenChoiceEntity(
                        roomIdValue,
                        currentUserId,
                        row.getOrderNo(),
                        row.getHand()))
                .toList();

        jankenChoiceJpaRepository.saveAll(choices);

    }

    @Override
    @Transactional
    public void executeBattle(Integer roomIdvalue) {

        RoomId roomId = new RoomId(roomIdvalue);
        // ルーム情報を取得
        RoomRegisterDto room = roomService.findById(roomId);

        // JankenMode を取得
        JankenMode mode = (JankenMode) room.getGameMode();

        // ルームに登録されている全プレイヤーID取得
        Set<PlayerId> allPlayers = roomPlayerJpaRepository.findPlayerIdByRoomId(roomIdvalue)
                .stream()
                .map(PlayerId::new)
                .collect(Collectors.toSet());

        // 全プレイヤーの出し手情報取得
        List<JankenChoiceRecord> choices = jankenChoiceJpaRepository
                .findByRoomIdOrderByOrderNoAsc(roomIdvalue)
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
        List<JankenPlayerResultRecord> playerResults = jankenResultService.calculatePlayerResults(
                roomId, mode, results,
                allPlayers);

        // プレイヤーの成績を保存
        savePlayerResults(roomId, playerResults);
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

    @Override
    public JankenRegisteredStatusDto getJankenRegisterdStatus(Integer roomId) {
        // ルーム内のプレイヤー一覧
        Set<Integer> roomPlayers = roomPlayerJpaRepository.findPlayerIdByRoomId(roomId);

        // 出し手一覧
        List<JankenChoiceEntity> hands = jankenChoiceJpaRepository
                .findByRoomIdOrderByOrderNoAsc(roomId);

        // 出し手を登録したプレイヤーIDの集合（キー存在で判定）
        Set<Integer> handSet = hands.stream()
                .map(JankenChoiceEntity::getPlayerId)
                .collect(Collectors.toSet());

        // プレイヤーID → UserName(VO) 
        Map<Integer, String> userNameMap = roomPlayers.stream()
                .collect(Collectors.toMap(
                        pid -> pid,
                        pid -> userJpaRepository.findByUserId(pid)
                                .orElseThrow()
                                .getUserName()));

        // 描画用DTO　playerStatusList 作成
        List<PlayerStatusDto> playerStatusList = roomPlayers.stream()
                .map(pid -> new PlayerStatusDto(
                        pid,
                        userNameMap.get(pid), // 名前
                        handSet.contains(pid) // 登録済み判定
                ))
                .toList();

        // 全員登録済みかどうか
        boolean allRegistered = playerStatusList.stream()
                .allMatch(PlayerStatusDto::handRegistered);

        // ログインユーザーがルームマスターかどうか
        boolean isRoomMaster = roomService.isRoomMaster(new RoomId(roomId));

        // 描画用DTO　JankenRegisteredStatusDto にまとめて返す
        return new JankenRegisteredStatusDto(
                playerStatusList,
                allRegistered,
                isRoomMaster);
    }

    // ② 結果参照（read）
    public BattleResultDto getBattleResult(Integer roomIdValue) {

        RoomId roomId = new RoomId(roomIdValue);
        RoomRegisterDto room = roomService.findById(roomId);

        // -------------------------
        // 1. プレイヤー成績（ランキング）
        // -------------------------
        List<PlayerResultView> playerResultViews = jankenPlayerResultJpaRepository
                .findByRoomId(roomIdValue)
                .stream()
                .map(e -> {
                    String name = userService.findUserNameById(e.getPlayerId())
                            .orElse("不明なユーザー");
                    return new PlayerResultView(
                            e.getPlayerId(),
                            name,
                            e.getWinCount(),
                            e.getLoseCount(),
                            e.getFinalRank());
                })
                .sorted(Comparator.comparing(PlayerResultView::finalRank)) // ランク順にソート
                .toList();

        // -------------------------
        // 2. ラウンド結果（勝敗）
        // -------------------------
        List<JankenRoundResultEntity> roundEntities = jankenRoundResultJpaRepository
                .findByRoomId(roomIdValue);

        Map<Integer, RoundResultView> roundResults = roundEntities.stream()
                .map(e -> new RoundResultView(
                        e.getOrderNo(),
                        e.isDraw(),
                        e.getWinnerPlayerIds(),
                        e.getLoserPlayerIds()))
                .collect(Collectors.toMap(
                        RoundResultView::orderNo,
                        v -> v,
                        (a, b) -> a,
                        LinkedHashMap::new // ラウンド順を保持
                ));

        // -------------------------
        // 3. ラウンドごとの手（誰が何を出したか）
        // -------------------------
        JankenMode mode = (JankenMode) room.getGameMode();

        List<JankenChoiceEntity> choiceEntities = jankenChoiceJpaRepository
                .findByRoomIdOrderByOrderNoAsc(roomIdValue);

        // 全手を orderNo ごとに groupBy
        Map<Integer, List<JankenChoiceEntity>> allHands = choiceEntities.stream()
                .collect(Collectors.groupingBy(
                        JankenChoiceEntity::getOrderNo,
                        LinkedHashMap::new,
                        Collectors.toList()));

        Map<Integer, List<RoundHandView>> roundHands = new LinkedHashMap<>();

        // 初期 activePlayers（全員）
        Set<Integer> activePlayers = new HashSet<>(
                playerResultViews.stream()
                        .map(PlayerResultView::playerId)
                        .toList());

        // プレイヤーID → 名前 の Map を作る
        Map<Integer, String> playerNameMap = playerResultViews.stream()
                .collect(Collectors.toMap(
                        PlayerResultView::playerId,
                        PlayerResultView::playerName));

        // ラウンド順に処理
        for (var entry : roundResults.entrySet()) {
            int orderNo = entry.getKey();
            RoundResultView result = entry.getValue();

            // このラウンドで戦ったプレイヤーだけを抽出
            List<RoundHandView> filtered = allHands.get(orderNo).stream()
                    .filter(e -> activePlayers.contains(e.getPlayerId()))
                    .map(e -> new RoundHandView(
                            e.getOrderNo(),
                            e.getPlayerId(),
                            playerNameMap.get(e.getPlayerId()),
                            e.getJankenHand()))
                    .sorted(Comparator.comparing(RoundHandView::hand))
                    .toList();

            roundHands.put(orderNo, filtered);

            // 次ラウンドの activePlayers を更新
            if (!result.isDraw()) {
                switch (mode) {
                    case WINNER_STAYS -> activePlayers.retainAll(result.winnerPlayerIds());
                    case LOSER_STAYS -> activePlayers.retainAll(result.loserPlayerIds());
                    case TOTAL_BATTLE -> {
                    } // 変化なし
                }
            }
        }

        // -------------------------
        // 4. DTO に詰めて返却
        // -------------------------
        return new BattleResultDto(
                room,
                playerResultViews,
                roundResults,
                roundHands);
    }

}
