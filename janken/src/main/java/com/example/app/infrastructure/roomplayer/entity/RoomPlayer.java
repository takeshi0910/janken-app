package com.example.app.infrastructure.roomplayer.entity;

import java.sql.Types;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;

import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_players")
@Getter
@NoArgsConstructor
public class RoomPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_player_id")
    private Integer id;

    @JdbcTypeCode(Types.INTEGER)
    @Column(name = "room_id", nullable = false)
    private RoomId roomId;

    @JdbcTypeCode(Types.INTEGER)
    @Column(name = "player_id", nullable = false)
    private PlayerId playerId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public RoomPlayer(RoomId roomId, PlayerId playerId) {
        this.roomId = roomId;
        this.playerId = playerId;
    }
}

