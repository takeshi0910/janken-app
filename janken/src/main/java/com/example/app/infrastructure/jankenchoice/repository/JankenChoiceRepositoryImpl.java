package com.example.app.infrastructure.jankenchoice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.domain.janken.model.JankenChoiceRecord;
import com.example.app.domain.janken.repository.JankenChoiceRepository;
import com.example.app.domain.janken.vo.OrderNo;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.jankenchoice.entity.JankenChoiceEntity;
import com.example.app.infrastructure.jankenchoice.jpa.JankenChoiceJpaRepository;

import lombok.RequiredArgsConstructor;

/** JankenChoice domain用repository 実装クラス */
@Repository
@RequiredArgsConstructor
public class JankenChoiceRepositoryImpl implements JankenChoiceRepository {

    private final JankenChoiceJpaRepository jpa;

    @Override
    public List<JankenChoiceRecord> findByRoomId(RoomId roomId) {
        return jpa.findByRoomId(roomId.value())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<JankenChoiceRecord> findByRoomIdAndPlayerId(RoomId roomId, PlayerId playerId) {
        return jpa.findByRoomIdAndPlayerId(roomId.value(), playerId.value())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteByRoomIdAndPlayerId(RoomId roomId, PlayerId playerId) {
        jpa.deleteByRoomIdAndPlayerId(roomId.value(), playerId.value());
    }
    
    @Override
    public void deleteByRoomId(RoomId roomId) {
        jpa.deleteByRoomId(roomId.value());
    }

    @Override
    public void saveAll(List<JankenChoiceRecord> choices) {
        List<JankenChoiceEntity> entities = choices.stream()
                .map(this::toEntity)
                .toList();

        jpa.saveAll(entities);
    }

    /** Entity → Domain　*/
    private JankenChoiceRecord toDomain(JankenChoiceEntity e) {
        return new JankenChoiceRecord(
                new RoomId(e.getRoomId()),
                new OrderNo(e.getOrderNo()),
                new PlayerId(e.getPlayerId()),
                e.getJankenHand());
    }

    /** Domain → Entity */
    private JankenChoiceEntity toEntity(JankenChoiceRecord d) {
        return new JankenChoiceEntity(
                d.roomId().value(),
                d.orderNo().value(),
                d.playerId().value(),
                d.jankenHand());
    }

}
