package com.example.app.game.janken.domain.model;

/** 
 * じゃんけん専用のゲームモードを表す列挙型
 * 
 * @author takeshi.kashiwagi
 */
public enum JankenMode {
    WINNER_STAYS("勝ち残り"),
    LOSER_STAYS("負け残り"),
    TOTAL_BATTLE("総当たり");

    private final String label;

    JankenMode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
