package com.example.app.domain.janken.model;

import java.util.Set;

import com.example.app.domain.room.vo.PlayerId;

/**
 * じゃんけんのラウンド毎の勝敗をデータを保持するモデルクラス
 * 
 * @author takeshi.kashiwagi
 */
public class RoundResult {
    
    private final boolean isDraw;
    private final Set<PlayerId> winners;
    private final Set<PlayerId> losers;

    private RoundResult(boolean isDraw,  Set<PlayerId> winners, Set<PlayerId> losers) {
        this.isDraw = isDraw;
        this.winners = winners;
        this.losers = losers;
    }

    // あいこの場合、勝者も敗者も登録なし。
    public static RoundResult draw() {
        return new RoundResult(true,  Set.of(), Set.of());
    }

    // 勝負が決定した場合、勝者も敗者をそれぞれ登録。
    public static RoundResult decided( Set<PlayerId> winners, Set<PlayerId> losers) {
        return new RoundResult(false,  winners, losers);
    }

    public boolean isDraw() {
        return isDraw;
    }

    public Set<PlayerId> getWinners() {
        return winners;
    }

    public Set<PlayerId> getLosers() {
        return losers;
    }

}
