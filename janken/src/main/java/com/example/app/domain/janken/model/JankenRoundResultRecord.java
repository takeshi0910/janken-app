package com.example.app.domain.janken.model;

import java.util.List;

import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;

/** janken_round_result ドメインモデル */
public record JankenRoundResultRecord(
        RoomId roomId,
        OrderNo orderNo,
        boolean isDraw,
        List<PlayerId> winnerPlayerIds,
        List<PlayerId> loserPlayerIds) {
}
