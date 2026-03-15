package com.example.app.room.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.app.room.domain.GameKind;
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
    private int roundCount;
    private RoomStatus roomStatus;
    private List<Integer> userIds;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    
    /**フォーム変換 */
    public RoomForm toForm() {
        RoomForm form = new RoomForm();
        form.setRoomId(this.roomId);
        form.setRoomName(this.roomName);
        form.setGameKind(this.gameKind);
        form.setRoundCount(this.roundCount);
        form.setRoomStatus(this.roomStatus);
        form.setUserIds(this.userIds);
        form.setStartedDate(this.startedDate);
        form.setEndDate(this.endDate);
        return form;
    }
}
