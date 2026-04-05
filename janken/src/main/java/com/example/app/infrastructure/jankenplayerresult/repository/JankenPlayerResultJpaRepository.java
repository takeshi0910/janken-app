package com.example.app.infrastructure.jankenplayerresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.jankenplayerresult.entity.JankenPlayerResultEntity;


/**
 * janken_player_resultテーブル repository
 * 
 * @author takeshi.kashiwagi
 */
public interface JankenPlayerResultJpaRepository
        extends JpaRepository<JankenPlayerResultEntity, Integer> {

    void deleteByRoomId(RoomId roomId);

    List<JankenPlayerResultEntity> findByRoomId(RoomId roomId);

}
