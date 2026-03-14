package com.example.app.game.room.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.app.game.room.domain.RoomUser;
import com.example.app.game.room.domain.RoomUserId;

public interface RoomUsersRepository  extends JpaRepository<RoomUser, RoomUserId> {

    @Modifying
    @Query("DELETE FROM RoomUser ru WHERE ru.id.roomId = :roomId")
    void deleteByRoomId(@Param("roomId") Integer roomId);
    
    @Query("SELECT ru.id.userId FROM RoomUser ru WHERE ru.id.roomId = :roomId")
    List<Integer> findUserIdsByRoomId(@Param("roomId") Integer roomId);

}

