package com.example.app.infrastructure.roomplayer.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.infrastructure.roomplayer.entity.RoomPlayerEntity;

@Repository
public interface RoomPlayerJpaRepository extends JpaRepository<RoomPlayerEntity, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("delete from RoomPlayerEntity rp where rp.roomId = :roomId")
    void deleteByRoomId(@Param("roomId") Integer roomId);

    // SELECT player_id FROM room_players WHERE room_id = ?
    @Query("select rp.playerId from RoomPlayerEntity rp where rp.roomId = :roomId")
    Set<Integer> findPlayerIdByRoomId(Integer roomId);

}
