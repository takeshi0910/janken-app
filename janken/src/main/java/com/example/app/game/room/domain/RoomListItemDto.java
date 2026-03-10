package com.example.app.game.room.domain;

import java.time.LocalDateTime;

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
    private String gameType;
    private String gameStatus;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private Integer createdId;
    private LocalDateTime updatedAt;
    private Integer updatedId;
}