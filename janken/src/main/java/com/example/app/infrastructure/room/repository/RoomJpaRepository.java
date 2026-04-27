package com.example.app.infrastructure.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.domain.room.RoomStatus;
import com.example.app.infrastructure.room.entity.RoomEntity;

@Repository
public interface RoomJpaRepository  extends JpaRepository<RoomEntity, Integer> {
    @Modifying
    @Query("UPDATE RoomEntity r SET r.roomStatus = :status WHERE r.roomId = :roomId")
    void updateStatus(@Param("roomId") Integer roomId, @Param("status") RoomStatus status);
}
