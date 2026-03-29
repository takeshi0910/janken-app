package com.example.app.room.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.room.domain.Room;
import com.example.app.room.domain.RoomId;

@Repository
public interface RoomRepository  extends JpaRepository<Room, RoomId> {
}
