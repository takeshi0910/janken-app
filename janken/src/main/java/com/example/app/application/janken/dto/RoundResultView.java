package com.example.app.application.janken.dto;

import java.util.List;

/**
 * じゃんけんゲームのラウンドごとの戦績情報。画面描画用。
 * 
 * @author takeshi.kashiwagi
 */
public record RoundResultView(
        int orderNo,
        boolean isDraw,
        List<Integer> winnerPlayerIds,
        List<Integer> loserPlayerIds
) {}