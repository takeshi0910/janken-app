package com.example.app.infrastructure.jankenroundresult.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.infrastructure.jankenroundresult.entity.JankenRoundResultEntity;

public interface JankenRoundResultJpaRepository
        extends JpaRepository<JankenRoundResultEntity, Integer> {

    List<JankenRoundResultEntity> findByRoomId(Integer roomId);

    List<JankenRoundResultEntity> findByRoomIdOrderByOrderNo(Integer roomId);

    JankenRoundResultEntity findByRoomIdAndOrderNo(Integer roomId, Integer orderNo);

    void deleteByRoomId(Integer roomId);
}
