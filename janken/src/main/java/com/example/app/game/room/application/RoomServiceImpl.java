package com.example.app.game.room.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.game.room.domain.Room;
import com.example.app.game.room.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;

/** 
 * ゲームの部屋
 * 
 * @author takeshi.kashiwagi
 * */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;

    @Override
    public List<Room> findRoomsByUserId(int userId) {
        return roomMapper.findRoomsByUserId(userId);
    }

}