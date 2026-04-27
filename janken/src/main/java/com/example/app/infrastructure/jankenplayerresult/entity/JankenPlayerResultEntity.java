package com.example.app.infrastructure.jankenplayerresult.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * janken_player_result テーブル entity
 *
 * <p>洗い替えのため updated 系は不要。
 */
@Entity
@Table(
    name = "janken_player_result",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_room_player",
            columnNames = {"room_id", "player_id"}
        )
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JankenPlayerResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_result_id")
    private Integer playerResultId;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "player_id", nullable = false)
    private Integer playerId;

    @Column(name = "win_count", nullable = false)
    private int winCount;

    @Column(name = "lose_count", nullable = false)
    private int loseCount;

    @Column(name = "final_rank")
    private Integer finalRank;

    @Column(
        name = "created_at",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private LocalDateTime createdAt;

    public JankenPlayerResultEntity(
            Integer roomId,
            Integer playerId,
            int winCount,
            int loseCount,
            Integer finalRank
    ) {
        this.roomId = roomId;
        this.playerId = playerId;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.finalRank = finalRank;
    }
}

