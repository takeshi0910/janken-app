package com.example.app.infrastructure.jankenroundresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.jankenroundresult.entity.JankenRoundResultEntity;

public interface JankenRoundResultJpaRepository
        extends JpaRepository<JankenRoundResultEntity, Integer> {

    List<JankenRoundResultEntity> findByRoomId(RoomId roomId);

    List<JankenRoundResultEntity> findByRoomIdOrderByOrderNo(RoomId roomId);

    JankenRoundResultEntity findByRoomIdAndOrderNo(RoomId roomId, OrderNo orderNo);

    void deleteByRoomId(RoomId roomId);
}
