package com.example.app.application.janken.dto;

import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.user.vo.UserName;

/**
 * じゃんけんゲームのプレイヤーの出し手の登録状況 
 * 
 * @author takeshi.kashiwagi
 */
public record PlayerStatusDto(
        PlayerId playerId,
        UserName name,
        boolean handRegistered) {
}