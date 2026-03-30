package com.example.app.room.application.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.app.game.core.GameKind;
import com.example.app.game.core.GameMode;
import com.example.app.room.domain.PlayerId;
import com.example.app.room.domain.RoomId;
import com.example.app.room.domain.RoomStatus;
import com.example.app.room.presentation.RoomForm;

import lombok.Data;

/**
 * ルームの編集画面用に反映させるデータを保持するDTO
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomRegisterDto {
    private RoomId roomId;
    private String roomName;
    private GameKind gameKind;
    private GameMode gameMode;
    private int roundCount;
    private RoomStatus roomStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private Set<PlayerId> playerIds;
    
    /**フォーム変換 */
    public RoomForm toForm() {
        
        // Interface -> String
        String modeLabel = (gameMode != null) ? gameMode.name() : null;
        
        // VO -> Integer
        Set<Integer> playerIdValues = this.playerIds.stream()
                .map(PlayerId::value)
                .collect(Collectors.toSet());

        return new RoomForm(
            this.roomId.value(),
            this.roomName,
            this.gameKind,
            modeLabel,
            this.roundCount,
            this.roomStatus,
            this.startedDate,
            this.endDate,
            playerIdValues
        );
    }

}
