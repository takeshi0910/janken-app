package com.example.app.infrastructure.jankenplayerresult.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.infrastructure.jankenplayerresult.entity.JankenPlayerResultEntity;

/**
 * janken_player_resultテーブル repository
 * 
 * @author takeshi.kashiwagi
 */
public interface JankenPlayerResultJpaRepository
        extends JpaRepository<JankenPlayerResultEntity, Integer> {

    void deleteByRoomId(Integer integer);

    List<JankenPlayerResultEntity> findByRoomId(Integer roomId);

}
