package com.example.app.domain.janken.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.app.domain.janken.model.JankenMode;
import com.example.app.domain.janken.model.JankenPlayerResultRecord;
import com.example.app.domain.janken.model.RoundResult;
import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;

public class JankenResultService {

    /** 
     * ラウンドごとの対戦結果を集計し、プレイヤーごとの成績を返す。
     * 
     *  @param mode じゃんけんモード
     *  @param result ラウンドごとの対戦結果
     *  @param allPlayers 全プレイヤーID
     *  @author takeshi.kashiwagi
     */
    public List<JankenPlayerResultRecord> calculatePlayerResults(
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

}
