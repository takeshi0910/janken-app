package com.example.app.infrastructure.jankenroundresult.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.app.infrastructure.jankenroundresult.entity.JankenRoundResultEntity;

public interface JankenRoundResultJpaRepository
        extends JpaRepository<JankenRoundResultEntity, Integer> {

    List<JankenRoundResultEntity> findByRoomId(Integer roomId);

    List<JankenRoundResultEntity> findByRoomIdOrderByOrderNo(Integer roomId);

    JankenRoundResultEntity findByRoomIdAndOrderNo(Integer roomId, Integer orderNo);

    @Modifying(clearAutomatically = true)
    @Query("""
            DELETE FROM JankenRoundResultEntity r
             WHERE r.roomId = :roomId
        """)
    void deleteByRoomId(@Param("roomId") Integer roomId);
}
