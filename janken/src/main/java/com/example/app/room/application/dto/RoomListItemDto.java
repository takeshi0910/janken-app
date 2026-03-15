package com.example.app.room.application.dto;

import java.time.LocalDateTime;

import com.example.app.room.domain.RoomStatus;

import lombok.Data;

/** 
 * マイページでの表示するルーム情報を保持するDTO
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class RoomListItemDto {
    private Integer roomId;
    private String roomName;
    private String gameKind;
    private RoomStatus roomStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private boolean isRoomMaster;
    private boolean isPlayer;
}