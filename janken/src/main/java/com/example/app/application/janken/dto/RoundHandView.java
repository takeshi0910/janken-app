package com.example.app.application.janken.dto;

import com.example.app.domain.janken.model.JankenHand;

/**
 * じゃんけんゲームのラウンドごとの出し手情報。画面描画用。
 * 
 * @author takeshi.kashiwagi
 */
public record RoundHandView( 
    int orderNo,
    Integer playerId,
    String playerName,
    JankenHand hand
) {
    public String imagePath() {
        return switch (hand) {
            case グー -> "/janken/img/gu.png";
            case チョキ -> "/janken/img/choki.png";
            case パー -> "/janken/img/pa.png";
        };
    }
}