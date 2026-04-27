package com.example.app.domain.janken.model;

import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;

/** janken_choice ドメインモデル */
public record JankenChoiceRecord(
        RoomId roomId,
        OrderNo orderNo,
        PlayerId playerId,
        JankenHand jankenHand) {
}