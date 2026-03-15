package com.example.app.game.room.janken.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.game.room.janken.domain.JankenChoice;

@Repository
public interface JankenChoiceRepository   extends JpaRepository<JankenChoice, Integer> {
    void deleteByRoomIdAndCreatedId(Integer roomId, Integer createdId);
}
