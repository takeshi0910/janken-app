package com.example.app.infrastructure.jankenroundresult.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.domain.janken.model.JankenRoundResultRecord;
import com.example.app.domain.janken.repository.JankenRoundResultRepository;
import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.jankenroundresult.entity.JankenRoundResultEntity;
import com.example.app.infrastructure.jankenroundresult.jpa.JankenRoundResultJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JankenRoundResultRepositoryImpl implements JankenRoundResultRepository {

    private final JankenRoundResultJpaRepository jpa;

    @Override
    public void deleteByRoomId(RoomId roomId) {
        jpa.deleteByRoomId(roomId.value());
    }
    
    @Override
    public void saveAll(List<JankenRoundResultRecord> records) {
        List<JankenRoundResultEntity> entities = records.stream()
                .map(r -> new JankenRoundResultEntity(
                        r.roomId().value(),
                        r.orderNo().value(),
                        r.isDraw(),
                        r.winnerPlayerIds().stream().map(PlayerId::value).toList(),
                        r.loserPlayerIds().stream().map(PlayerId::value).toList()))
                .toList();

        jpa.saveAll(entities);
    }

    @Override
    public List<JankenRoundResultRecord> findByRoomId(RoomId roomId) {
        return jpa.findByRoomId(roomId.value()).stream()
                .map(e -> new JankenRoundResultRecord(
                        new RoomId(e.getRoomId()),
                        new OrderNo(e.getOrderNo()),
                        e.isDraw(),
                        e.getWinnerPlayerIds().stream().map(PlayerId::new).toList(),
                        e.getLoserPlayerIds().stream().map(PlayerId::new).toList()))
                .toList();
    }

}
