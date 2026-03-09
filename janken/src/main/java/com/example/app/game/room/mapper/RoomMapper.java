package com.example.app.game.room.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.app.game.room.domain.Room;

@Mapper
public interface RoomMapper {
    List<Room> findRoomsByUserId(@Param("userId") int userId);
}

