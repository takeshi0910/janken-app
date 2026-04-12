package com.example.app.application.room.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.app.domain.game.core.GameKind;
import com.example.app.domain.game.core.GameMode;
import com.example.app.domain.room.RoomStatus;
import com.example.app.presentation.room.RoomForm;

import lombok.Data;

/**
 * ルームの編集画面用に反映させるデータを保持するDTO
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomRegisterDto {
    private Integer roomId;
    private String roomName;
    private GameKind gameKind;
    private GameMode gameMode;
    private int roundCount;
    private RoomStatus roomStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private Set<Integer> playerIds;
    
    /**フォーム変換 */
    public RoomForm toForm() {
        
        // Interface -> String
        String modeLabel = (gameMode != null) ? gameMode.name() : null;
        
        return new RoomForm(
            this.roomId,
            this.roomName,
            this.gameKind,
            modeLabel,
            this.roundCount,
            this.roomStatus,
            this.startedDate,
            this.endDate,
            playerIds
        );
    }

}
