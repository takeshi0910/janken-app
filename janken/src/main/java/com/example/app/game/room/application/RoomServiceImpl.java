package com.example.app.game.room.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.game.room.domain.Room;
import com.example.app.game.room.domain.RoomListItemDto;
import com.example.app.game.room.domain.repository.RoomRepository;
import com.example.app.game.room.infrastructure.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;

/** 
 * ROOMに関連するサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 * */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;

    @Override
    public List<RoomListItemDto> selectRoomsByUserId(int userId) {
        return roomMapper.selectRoomsByUserId(userId);
    }
    
    @Override
    public Room createRoom(Room room, int userId) {
    
        room.setCreatedAt(LocalDateTime.now());
        room.setCreatedId(userId);
    
        room.setUpdatedAt(LocalDateTime.now());
        room.setUpdatedId(userId);
    
        return roomRepository.save(room);
    }
    
    @Override
    public Room updateRoom(Room room, int userId) {
    
        room.setUpdatedAt(LocalDateTime.now());
        room.setUpdatedId(userId);
    
        return roomRepository.save(room);
    }

}