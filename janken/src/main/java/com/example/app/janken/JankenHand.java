package com.example.app.janken;

/** 
 * じゃんけんの手を表す列挙型。
 * 勝敗を決定するロジックを持つ。
 * 
 * @author takeshi.kashiwagi
 */
public enum JankenHand {
    グー, チョキ, パー;

    public boolean isWin(JankenHand opponent) {
        return switch (this) {
            case グー -> opponent == チョキ;
            case チョキ -> opponent == パー;
            case パー -> opponent == グー;
        };
    }

    public JankenResult judge(JankenHand opponent) {
        if (this == opponent)
            return JankenResult.DRAW;
        return this.isWin(opponent) ? JankenResult.WIN : JankenResult.LOSE;
    }

}