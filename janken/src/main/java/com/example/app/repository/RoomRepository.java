package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.Room;

/**
 * @author masatoki.toyama
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
}

