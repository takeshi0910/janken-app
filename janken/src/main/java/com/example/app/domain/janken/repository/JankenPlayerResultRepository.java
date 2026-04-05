package com.example.app.domain.janken.repository;

import java.util.List;

import com.example.app.domain.janken.model.JankenPlayerResultRecord;
import com.example.app.domain.room.vo.RoomId;

public interface JankenPlayerResultRepository {
    void deleteByRoomId(RoomId roomId);

    void saveAll(List<JankenPlayerResultRecord> records);

    List<JankenPlayerResultRecord> findByRoomId(RoomId roomId);
}
