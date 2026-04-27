package com.example.app.infrastructure.jankenroundresult.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.example.app.infrastructure.jpa.IntegerListJsonConverter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * janken_round_result テーブル entity
 */
@Entity
@Table(
    name = "janken_round_result",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_room_order",
            columnNames = { "room_id", "order_no" }
        )
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JankenRoundResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_result_id")
    private Integer roundResultId;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "order_no", nullable = false)
    private Integer orderNo;

    @Column(name = "is_draw", nullable = false)
    private boolean isDraw;

    @Convert(converter = IntegerListJsonConverter.class)
    @Column(name = "winner_player_ids", nullable = false, columnDefinition = "json")
    private List<Integer> winnerPlayerIds;

    @Convert(converter = IntegerListJsonConverter.class)
    @Column(name = "loser_player_ids", nullable = false, columnDefinition = "json")
    private List<Integer> loserPlayerIds;

    @Column(
        name = "created_at",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private LocalDateTime createdAt;

    public JankenRoundResultEntity(
            Integer roomId,
            Integer orderNo,
            boolean isDraw,
            List<Integer> winnerPlayerIds,
            List<Integer> loserPlayerIds
    ) {
        this.roomId = roomId;
        this.orderNo = orderNo;
        this.isDraw = isDraw;
        this.winnerPlayerIds = winnerPlayerIds;
        this.loserPlayerIds = loserPlayerIds;
    }
}
