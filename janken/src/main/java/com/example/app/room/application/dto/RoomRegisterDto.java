package com.example.app.room.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.app.game.core.GameKind;
import com.example.app.room.domain.RoomStatus;
import com.example.app.room.web.RoomForm;

import lombok.Data;

/**
 * ルームの編集画面用に反映させるデータを保持するDTO
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomRegisterDto {
    private int roomId;
    private String roomName;
    private GameKind gameKind;
    private String gameMode;
    private int roundCount;
    private RoomStatus roomStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private List<Integer> userIds;
    
    /**フォーム変換 */
    public RoomForm toForm() {
        return new RoomForm(
            this.roomId,
            this.roomName,
            this.gameKind,
            this.gameMode,
            this.roundCount,
            this.roomStatus,
            this.startedDate,
            this.endDate,
            this.userIds
        );
    }

}
