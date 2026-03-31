package com.example.app.domain.janken.model;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.app.domain.room.PlayerId;
import com.example.app.infrastructure.janken.entity.JankenChoice;

/**
 * じゃんけんゲームの勝敗判定ロジックを提供するエンジンクラス。
 * 
 * <p>
 * 本クラスは状態を保持せず、与えられた入力（プレイヤーの選択や
 * アクティブプレイヤー集合）に基づいて勝敗判定を行う純粋な
 * ドメインサービスとして機能する。

 * <p>主な責務：
 * <ul>
 *   <li>1ラウンド分の勝敗判定（judgeRound）の実行</li>
 *   <li>モード別（総当たり／勝ち残り／負け残り）の判定ロジック提供
 *   <li>入力値をもとに判定結果を返す</li>
 * </ul>
 * 
 * @author takeshi.kashiwagi
 */
@Service
public class JankenGameEngine {

    /**
     * モードに応じて勝敗判定を実行する。
     *
     * @param mode          じゃんけんモード
     * @param choices       全プレイヤーの選択
     * @param activePlayers 判定対象プレイヤー
     * @return ラウンド結果
     */
    public Map<OrderNo, RoundResult> judge(
            JankenMode mode,
            List<JankenChoice> choices,
            Set<PlayerId> allPlayers,
            int maxRounds) {

        // activePlayers はモードによってはラウンドごとに変化するためコピーを使用
        Set<PlayerId> activePlayers = new HashSet<>(allPlayers);

        return switch (mode) {
            case TOTAL_BATTLE -> judgeTotalBattle(choices, activePlayers, maxRounds);
            case WINNER_STAYS -> judgeWinnerStays(choices, activePlayers, maxRounds);
            case LOSER_STAYS -> judgeLoserStays(choices, activePlayers, maxRounds);
        };
    }
    
    /**
     * 1回分の勝敗判定を行う。
     * 
     * <p>activePlayers に含まれるプレイヤーのみを対象に、
     * 出した手ごとにプレイヤーをグルーピングし、勝敗を決定する。
     *
     * @param choices       全プレイヤーの選択
     * @param activePlayers 判定対象となるプレイヤーID集合
     * @return ラウンド結果（勝者・敗者・あいこ）
     */
    public RoundResult judgeRound(List<JankenChoice> choices, Set<PlayerId> activePlayers) {
        // 有効プレイヤーだけを対象に、手ごとにプレイヤーIDをグルーピングして Map にしている
        Map<JankenHand, Set<PlayerId>> grouped = choices.stream()
                .filter(c -> activePlayers.contains(c.getPlayerId()))
                .collect(Collectors.groupingBy(
                        JankenChoice::getJankenHand,
                        Collectors.mapping(JankenChoice::getPlayerId, Collectors.toSet())));

        Set<JankenHand> hands = grouped.keySet();

        // あいこ判定：全員同じ手(1種類）もしくは全種類（3種類)。
        if (hands.size() == 1 || hands.size() == 3) {
            return RoundResult.draw();
        }

        // 勝ち手を決定（ここで size は必ず 2となる。）
        JankenHand winningHand = JankenHand.findWinner(hands);

        // 勝者（勝ち手を出したプレイヤー）
        Set<PlayerId> winners = grouped.get(winningHand);

        // 敗者（勝者以外のプレイヤー）
        Set<PlayerId> losers = activePlayers.stream()
                .filter(p -> !winners.contains(p))
                .collect(Collectors.toSet());

        return RoundResult.decided(winners, losers);
    }

    /**
     * 総当たり戦の勝敗判定を行う。
     * 全プレイヤーを対象に1回分の勝敗判定を実行する。
     *
     * @param choices 全プレイヤーの選択
     * @return 各ラウンドの勝負判定結果
     */
    public Map<OrderNo, RoundResult> judgeTotalBattle(
            List<JankenChoice> choices,
            Set<PlayerId> activePlayers,
            int maxRounds) {
        Map<OrderNo, RoundResult> results = new LinkedHashMap<>();

        for (int order = 1; order <= maxRounds; order++) {

            // 1ラウンドの勝敗判定
            RoundResult result = judgeRound(choices, activePlayers);

            // order_no を付与して保存
            results.put(new OrderNo(order), result);

            // 総当たり戦は activePlayers を更新しない。全員が最後まで参加する。
        }

        return results;
    }

    /**
     * 勝ち残り戦の勝敗判定を行う。
     * 各ラウンドごとに勝者のみを activePlayers に残し、敗者を除外する。
     *
     * @param choices       全プレイヤーの選択
     * @param activePlayers 現在勝ち残っているプレイヤーID
     * @param maxRounds 最大ラウンド数
     * @return 各ラウンドの勝負判定結果
     */
    public Map<OrderNo, RoundResult> judgeWinnerStays(List<JankenChoice> choices,
            Set<PlayerId> activePlayers,
            int maxRounds) {

        Map<OrderNo, RoundResult> results = new LinkedHashMap<>();

        for (int order = 1; order <= maxRounds; order++) {

            // 1ラウンドの勝敗判定
            RoundResult result = judgeRound(choices, activePlayers);

            // 結果を保存
            results.put(new OrderNo(order), result);

            // 勝者だけを次ラウンドに残す
            Set<PlayerId> winners = result.getWinners();

            // 引き分け（勝者なし）の場合は全員残す
            if (!winners.isEmpty()) {
                activePlayers.retainAll(winners);
            }

            // 勝者が1人になったら終了
            if (activePlayers.size() <= 1) {
                break;
            }

        }

        return results;
    }

    /**
     * 負け残り戦の勝敗判定を行う。
     * 各ラウンドごとに敗者のみを activePlayers に残し、勝者を除外する。
     *
     * @param choices       全プレイヤーの選択
     * @param activePlayers 現在負け残っているプレイヤーID
     * @param maxRounds     最大ラウンド数
     * @return 各ラウンドの勝負判定結果
     */
    public Map<OrderNo, RoundResult> judgeLoserStays(
            List<JankenChoice> choices,
            Set<PlayerId> activePlayers,
            int maxRounds) {

        Map<OrderNo, RoundResult> results = new LinkedHashMap<>();

        for (int order = 1; order <= maxRounds; order++) {

            // 1ラウンドの勝敗判定
            RoundResult result = judgeRound(choices, activePlayers);

            // 結果を保存
            results.put(new OrderNo(order), result);

            // 敗者だけを次ラウンドに残す
            Set<PlayerId> losers = result.getLosers();

            // 引き分け（勝者なし）の場合は全員残す
            if (!losers.isEmpty()) {
                activePlayers.retainAll(losers);
            }

            // 残りが1人になったら終了
            if (activePlayers.size() <= 1) {
                break;
            }
        }

        return results;
    }

}
