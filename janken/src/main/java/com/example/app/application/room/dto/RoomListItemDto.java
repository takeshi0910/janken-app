package com.example.app.application.room.dto;

import java.time.LocalDateTime;

import com.example.app.domain.room.RoomId;
import com.example.app.domain.room.RoomStatus;

import lombok.Data;

/** 
 * マイページでの表示するルーム情報を保持するDTO
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomListItemDto {
    private RoomId roomId;
    private String roomName;
    private String gameKind;
    private String gameMode;
    private RoomStatus roomStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private boolean isRoomMaster;
    private boolean isPlayer;
    
    /**  じゃんけんの出し手の登録有無判定。DB由来ではない */
    private Boolean isHandRegistered;
}