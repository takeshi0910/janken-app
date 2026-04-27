package com.example.app.infrastructure.jankenchoice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.example.app.domain.janken.model.JankenHand;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * janken_choice テーブル entity
 *
 * <p>本人しか編集できないため updated_id は不要。
 * また、洗い替えするため updated_at も不要。
 */
@Entity
@Table(
    name = "janken_choice",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_room_player_order",
            columnNames = { "room_id", "player_id", "order_no" }
        )
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JankenChoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Integer choiceId;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "player_id", nullable = false)
    private Integer playerId;

    @Column(name = "order_no", nullable = false)
    private Integer orderNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "janken_hand", nullable = false, length = 100)
    private JankenHand jankenHand;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public JankenChoiceEntity(
            Integer roomId,
            Integer playerId,
            Integer orderNo,
            JankenHand jankenHand
    ) {
        this.roomId = roomId;
        this.playerId = playerId;
        this.orderNo = orderNo;
        this.jankenHand = jankenHand;
    }
}