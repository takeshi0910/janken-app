package com.example.app.game.room.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.game.room.domain.Room;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer> {
}
