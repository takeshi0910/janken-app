package com.example.app.application.janken.dto;

import java.util.List;
import java.util.Map;

import com.example.app.application.room.dto.RoomRegisterDto;

/**
 * じゃんけんの対戦結果描画用DTO
 * 
 * @takeshi.kashiwagi
 */
public record BattleResultDto(
        RoomRegisterDto room,
        List<PlayerResultView> ranking,
        Map<Integer, RoundResultView> roundResults,
        Map<Integer, List<RoundHandView>> roundHands
) {}
