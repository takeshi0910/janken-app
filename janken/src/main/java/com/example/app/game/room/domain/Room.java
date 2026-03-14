package com.example.app.game.room.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.app.game.domain.GameKind;

import lombok.Data;

@Entity
@Table(name = "room")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    @Column(name = "game_kind", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private GameKind gameKind;
    
    @Column(name = "round_count", nullable = false)
    private Integer roundCount;

    @Column(name = "room_status", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @CreatedDate // 登録日時の自動反映
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 更新日時の自動反映
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 作成者しか更新できないため、updated_idは不要
    @CreatedBy
    @Column(name = "created_id")
    private Integer createdId;

}
