package com.example.app.infrastructure.jankenplayerresult.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.app.infrastructure.jankenplayerresult.entity.JankenPlayerResultEntity;

/**
 * janken_player_resultテーブル repository
 * 
 * @author takeshi.kashiwagi
 */
public interface JankenPlayerResultJpaRepository
        extends JpaRepository<JankenPlayerResultEntity, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("""
        DELETE FROM JankenPlayerResultEntity r
         WHERE r.roomId = :roomId
    """)
    void deleteByRoomId(@Param("roomId") Integer roomId);

    List<JankenPlayerResultEntity> findByRoomId(Integer roomId);

}
