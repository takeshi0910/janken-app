package com.example.app.infrastructure.jankenplayerresult.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.domain.janken.model.JankenPlayerResultRecord;
import com.example.app.domain.janken.repository.JankenPlayerResultRepository;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.jankenplayerresult.entity.JankenPlayerResultEntity;
import com.example.app.infrastructure.jankenplayerresult.jpa.JankenPlayerResultJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JankenPlayerResultRepositoryImpl implements JankenPlayerResultRepository {

    private final JankenPlayerResultJpaRepository jpa;

    @Override
    public void deleteByRoomId(RoomId roomId) {
        jpa.deleteByRoomId(roomId.value());
    }

    @Override
    public void saveAll(List<JankenPlayerResultRecord> records) {
        List<JankenPlayerResultEntity> entities = records.stream()
                .map(r -> new JankenPlayerResultEntity(
                        r.roomId().value(),
                        r.playerId().value(),
                        r.winCount(),
                        r.loseCount(),
                        r.finalRank()
                ))
                .toList();

        jpa.saveAll(entities);
    }

    @Override
    public List<JankenPlayerResultRecord> findByRoomId(RoomId roomId) {
        return jpa.findByRoomId(roomId.value()).stream()
                .map(e -> new JankenPlayerResultRecord(
                        new RoomId(e.getRoomId()),
                        new PlayerId(e.getPlayerId()),
                        e.getWinCount(),
                        e.getLoseCount(),
                        e.getFinalRank()
                ))
                .toList();
    }
}
