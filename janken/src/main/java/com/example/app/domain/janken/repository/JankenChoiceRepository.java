package com.example.app.domain.janken.repository;

import java.util.List;

import com.example.app.domain.janken.model.JankenChoiceRecord;
import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;

/** JankenChoice domain用repository インターフェース */
public interface JankenChoiceRepository {

    List<JankenChoiceRecord> findByRoomId(RoomId roomId);

    List<JankenChoiceRecord> findByRoomIdAndPlayerId(RoomId roomId, PlayerId playerId);

    void deleteByRoomIdAndPlayerId(RoomId roomId, PlayerId playerId);

    void saveAll(List<JankenChoiceRecord> choices);

    void deleteByRoomId(RoomId roomId);

}