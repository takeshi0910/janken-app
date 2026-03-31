package com.example.app.domain.janken.model;

import com.example.app.domain.game.core.GameMode;

/** 
 * じゃんけん専用のゲームモードを表す列挙型
 * 
 * @author takeshi.kashiwagi
 */
public enum JankenMode  implements GameMode{
    WINNER_STAYS("勝ち残り"),
    LOSER_STAYS("負け残り"),
    TOTAL_BATTLE("総当たり");

    private final String label;

    JankenMode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
    
    /** 文字列からEnumを返す */
    public static JankenMode fromString(String value) {
        for (JankenMode mode : values()) {
            if (mode.name().equalsIgnoreCase(value) ||
                mode.label.equals(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Unknown JankenMode: " + value);
    }

}
