package com.example.app.game.room.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    @Column(name = "game_kind", nullable = false, length = 45)
    private String gameType;

    @Column(name = "room_status", nullable = false, length = 45)
    private String gameStatus;

    @Column(name = "started_date", nullable = false)
    private LocalDateTime startedDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_id")
    private Integer createdId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_id")
    private Integer updatedId;
}
