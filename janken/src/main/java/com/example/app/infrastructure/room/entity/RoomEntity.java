package com.example.app.infrastructure.room.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.app.domain.game.core.GameKind;
import com.example.app.domain.room.RoomStatus;

import lombok.Data;

/**
 * roomテーブル entity
 * 
 * <p>作成者しかルームを編集できないため、updated_idは不要
 */
@Entity
@Table(name = "room")
@Data
@EntityListeners(AuditingEntityListener.class)
public class RoomEntity {

    @Id
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    @Column(name = "game_kind", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private GameKind gameKind;
    
    // JPA登録に際してGameModeインターフェースでは対応できないためStringで登録
    @Column(name = "game_mode", length = 45) 
    private String gameMode;
    
    @Column(name = "round_count", nullable = false)
    private Integer roundCount;

    @Column(name = "room_status", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
    
    @CreatedBy
    @Column(name = "created_id")
    private Integer createdId;
    
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

}
