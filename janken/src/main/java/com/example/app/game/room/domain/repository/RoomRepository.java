package com.example.app.game.room.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.game.room.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}