package com.example.app.application.janken.dto;

import java.util.List;

/**
 * じゃんけんゲームの全プレイヤーの出し手の登録状況のモーダル描画用
 * 
 * @author takeshi.kashiwagi
 */
public record JankenRegisteredStatusDto(
        List<PlayerStatusDto> players, 
        boolean allRegistered,
        boolean isRoomMaster) {
}
