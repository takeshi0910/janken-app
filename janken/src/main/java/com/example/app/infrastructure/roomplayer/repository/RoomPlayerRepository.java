package com.example.app.infrastructure.roomplayer.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.roomplayer.entity.RoomPlayer;

@Repository
public interface RoomPlayerRepository extends JpaRepository<RoomPlayer, Integer> {

    // DELETE FROM room_players WHERE room_id = ?
    void deleteByRoomId(RoomId roomId);

    // SELECT player_id FROM room_players WHERE room_id = ?
    @Query("select rp.playerId from RoomPlayer rp where rp.roomId = :roomId")
    Set<PlayerId> findPlayerIdsByRoomId(RoomId roomId);

}
