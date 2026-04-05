package com.example.app.domain.janken.repository;

import java.util.List;

import com.example.app.domain.janken.model.JankenRoundResultRecord;
import com.example.app.domain.room.vo.RoomId;

public interface JankenRoundResultRepository {
    void deleteByRoomId(RoomId roomId);

    void saveAll(List<JankenRoundResultRecord> records);

    List<JankenRoundResultRecord> findByRoomId(RoomId roomId);

}
