package com.example.app.game.room.domain;

import java.time.LocalDateTime;

import lombok.Data;

/** 
 * ゲームの部屋
 * 
 * @author takeshi.kashiwagi
 * */
@Data
public class Room {
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

