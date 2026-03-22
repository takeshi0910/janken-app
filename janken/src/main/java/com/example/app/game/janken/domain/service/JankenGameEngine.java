package com.example.app.game.janken.domain.service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.app.game.janken.domain.model.JankenHand;
import com.example.app.game.janken.domain.model.JankenMode;
import com.example.app.game.janken.domain.model.round.OrderNo;
import com.example.app.game.janken.domain.model.round.RoundResult;
import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;

/**
 * じゃんけんゲームの進行を管理するエンジンクラス。
 * <p>
 * 本クラスは以下の責務を持つ：
 * <ul>
 *   <li>ゲームに参加するプレイヤーとその選択（JankenChoice）の保持</li>
 *   <li>アクティブプレイヤー（勝敗判定対象）の管理</li>
 *   <li>1回分の勝敗判定（judgeRound）の実行</li>
 *   <li>モード別の進行（総当たり／勝ち残り／負け残り）の提供</li>
 * </ul>
 * 
 * ゲームの状態（activePlayers）はラウンド結果に応じて変化する。
 * そのため、本クラスは状態を持つオブジェクトとして設計されている。
 * 
 * @author takeshi.kashiwagi
 */
public class JankenGameEngine {

    private List<JankenChoice> choices;

    /** 現在のラウンドで勝敗判定対象となるプレイヤーID集合 */
    private Set<Integer> activePlayers;

    /**
     * コンストラクタ。
     * ゲーム開始時点での全プレイヤーの選択を受け取り、
     * activePlayers を全プレイヤーで初期化する。
     *
     * @param choices 全プレイヤーの選択リスト
     */
    public JankenGameEngine(List<JankenChoice> choices) {
        this.choices = choices;
        this.activePlayers = extractAllPlayers(choices);
    }

    /**
     * 1回分の勝敗判定を行う。
     * <p>
     * activePlayers に含まれるプレイヤーのみを対象に、
     * 出した手ごとにプレイヤーをグルーピングし、勝敗を決定する。
     *
     * @param choices       全プレイヤーの選択
     * @param activePlayers 判定対象となるプレイヤーID集合
     * @return ラウンド結果（勝者・敗者・あいこ）
     */
    public RoundResult judgeRound(List<JankenChoice> choices, Set<Integer> activePlayers) {
        // 有効プレイヤーだけを対象に、手ごとにプレイヤーIDをグルーピングして Map にしている
        Map<JankenHand, Set<Integer>> grouped = choices.stream()
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
        Set<Integer> winners = grouped.get(winningHand);

        // 敗者（勝者以外のプレイヤー）
        Set<Integer> losers = activePlayers.stream()
                .filter(p -> !winners.contains(p))
                .collect(Collectors.toSet());

        return RoundResult.decided(winners, losers);
    }

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
            Set<Integer> allPlayers,
            int maxRounds) {

        // activePlayers はモードによってはラウンドごとに変化するためコピーを使用
        Set<Integer> activePlayers = new HashSet<>(allPlayers);

        return switch (mode) {
            case TOTAL_BATTLE -> judgeTotalBattle(choices, activePlayers, maxRounds);
            case WINNER_STAYS -> judgeWinnerStays(choices, activePlayers, maxRounds);
            case LOSER_STAYS -> judgeLoserStays(choices, activePlayers, maxRounds);
        };
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
            Set<Integer> activePlayers,
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
            Set<Integer> activePlayers,
            int maxRounds) {

        Map<OrderNo, RoundResult> results = new LinkedHashMap<>();

        for (int order = 1; order <= maxRounds; order++) {

            // 1ラウンドの勝敗判定
            RoundResult result = judgeRound(choices, activePlayers);

            // 結果を保存
            results.put(new OrderNo(order), result);

            // 勝者だけを次ラウンドに残す
            Set<Integer> winners = result.getWinners();

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
            Set<Integer> activePlayers,
            int maxRounds) {

        Map<OrderNo, RoundResult> results = new LinkedHashMap<>();

        for (int order = 1; order <= maxRounds; order++) {

            // 1ラウンドの勝敗判定
            RoundResult result = judgeRound(choices, activePlayers);

            // 結果を保存
            results.put(new OrderNo(order), result);

            // 敗者だけを次ラウンドに残す
            Set<Integer> losers = result.getLosers();

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

    /**
     * choices から全プレイヤーIDを抽出する。
     *
     * @param choices 全プレイヤーの選択
     * @return プレイヤーID集合
     */
    private Set<Integer> extractAllPlayers(List<JankenChoice> choices) {
        return choices.stream()
                .map(JankenChoice::getPlayerId)
                .collect(Collectors.toSet());
    }

}
