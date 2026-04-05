package com.example.app.domain.janken.model;

import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;

/** janken_player_result ドメインモデル */
public record JankenPlayerResultRecord(
        RoomId roomId,
        PlayerId playerId,
        int winCount,
        int loseCount,
        Integer finalRank) {
}
