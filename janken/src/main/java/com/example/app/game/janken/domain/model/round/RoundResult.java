package com.example.app.game.janken.domain.model.round;

import java.util.Set;

/**
 * じゃんけんのターン毎の勝敗をデータを保持するモデルクラス
 * 
 * @author takeshi.kashiwagi
 */
public class RoundResult {
    
    private final boolean isDraw;
    private final Set<Integer> winners;
    private final Set<Integer> losers;

    private RoundResult(boolean isDraw,  Set<Integer> winners, Set<Integer> losers) {
        this.isDraw = isDraw;
        this.winners = winners;
        this.losers = losers;
    }

    // あいこの場合、勝者も敗者も登録なし。
    public static RoundResult draw() {
        return new RoundResult(true,  Set.of(), Set.of());
    }

    // 勝負が決定した場合、勝者も敗者をそれぞれ登録。
    public static RoundResult decided( Set<Integer> winners, Set<Integer> losers) {
        return new RoundResult(false,  winners, losers);
    }

    public boolean isDraw() {
        return isDraw;
    }

    public Set<Integer> getWinners() {
        return winners;
    }

    public Set<Integer> getLosers() {
        return losers;
    }


}
