package com.example.app.game.janken.presentation;

import java.util.ArrayList;
import java.util.List;

import com.example.app.game.janken.domain.model.JankenHand;

import lombok.Getter;
import lombok.Setter;

/** 
 * じゃんけんプレイ画面の入力値を保持するフォーム。
 * 各回の出し手とその順番を ChoiceRow として保持する。
 * 
 * @author takeshi.kashiwagi
 */
@Getter
@Setter
public class JankenChoiceForm {

    /** ルームID */
    private Integer roomId;

    /** 出し手とその順番を保持するリスト */
    private List<ChoiceRow> choices = new ArrayList<>();

    /** 1回分の出し手とその順番を保持する行データ */
    @Getter
    @Setter
    public static class ChoiceRow {
        private Integer orderNo;
        private JankenHand hand;
    }

}
