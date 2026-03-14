package com.example.app.game.room.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.game.room.domain.Room;
import com.example.app.game.room.domain.RoomListItemDto;
import com.example.app.game.room.infrastructure.mapper.RoomMapper;
import com.example.app.game.room.infrastructure.repository.RoomRepository;
import com.example.app.game.room.web.RoomForm;

import lombok.RequiredArgsConstructor;

/** 
 * ROOMに関連するサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
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
    public Room findById(int roomId) {
        return roomRepository.findById(roomId)
                        .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomId));
    }

    @Override
    @Transactional
    public void save(RoomForm form) {
        Room entity = form.toEntity();
        roomRepository.save(entity);
        
        roomUserRepository.deleteByRoomId(form.getRoomId());

        for (Integer userId : form.getUserIds()) {
            roomUserRepository.insert(form.getRoomId(), userId);
        }
        
    }
}