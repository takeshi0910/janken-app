package com.example.app.infrastructure.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.room.entity.Room;

@Repository
public interface RoomRepository  extends JpaRepository<Room, RoomId> {
}
