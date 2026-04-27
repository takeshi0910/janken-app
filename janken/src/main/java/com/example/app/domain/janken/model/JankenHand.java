package com.example.app.domain.janken.model;

import java.util.Iterator;
import java.util.Set;

/** 
 * じゃんけんの手を表す列挙型。
 * 勝敗を決定するロジックを持つ。
 * 
 * @author takeshi.kashiwagi
 */
public enum JankenHand {
    グー,
    チョキ,
    パー;

    /** この手が other に勝つなら true */
    public boolean isWin(JankenHand other) {
        return (this == グー && other == チョキ)
                || (this == チョキ && other == パー)
                || (this == パー && other == グー);
    }

    /** 2種類の手から勝ち手を返す（総当たりモード用） */
    public static JankenHand findWinner(Set<JankenHand> hands) {
        if (hands.size() != 2) {
            throw new IllegalArgumentException("勝ち手判定は2種類の手のときのみ有効です: " + hands);
        }

        Iterator<JankenHand> it = hands.iterator();
        JankenHand h1 = it.next();
        JankenHand h2 = it.next();

        if (h1.isWin(h2)) {
            return h1;
        } else if (h2.isWin(h1)) {
            return h2;
        }

        // 同じ手は size==1 の時点で弾いているのでここには来ない
        throw new IllegalStateException("勝敗が決定できません: " + hands);
    }

}