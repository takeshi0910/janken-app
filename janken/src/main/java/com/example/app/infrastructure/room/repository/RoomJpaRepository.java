package com.example.app.infrastructure.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.infrastructure.room.entity.RoomEntity;

@Repository
public interface RoomJpaRepository  extends JpaRepository<RoomEntity, Integer> {
}
