package com.example.app.application.janken.dto;

/**
 * じゃんけんゲームのプレイヤーの出し手の登録状況 (登録済or未登録）
 * 
 * @author takeshi.kashiwagi
 */
public record PlayerStatusDto(
        Integer playerId,
        String playerName,
        boolean handRegistered) {
}