package com.example.app.game.janken.infrastructure.persistence.model;

import com.example.app.room.domain.PlayerId;
import com.example.app.room.domain.RoomId;

import lombok.Getter;

/**
 * janken_player_resultテーブルの登録用POJO
 * 
 * @author takeshi.kashiwagi
 */
@Getter
public class JankenPlayerResultRecord{
    
    private final RoomId roomId;
    private final PlayerId playerId;
    private final int winCount;
    private final int loseCount;
    private final Integer finalRank; // nullable 不参加の人用

    public JankenPlayerResultRecord(
            RoomId roomId,
            PlayerId playerId,
            int winCount,
            int loseCount,
            Integer finalRank) {
        this.roomId = roomId;
        this.playerId = playerId;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.finalRank = finalRank;
    }
}
