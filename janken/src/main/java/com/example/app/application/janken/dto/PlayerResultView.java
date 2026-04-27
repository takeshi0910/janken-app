package com.example.app.application.janken.dto;

/**
 * じゃんけんゲームのプレイヤーごとの戦績情報。画面描画用。
 * 
 * @author takeshi.kashiwagi
 */
public record PlayerResultView(
        Integer playerId,
        String playerName,
        int winCount,
        int loseCount,
        Integer finalRank
) {}
