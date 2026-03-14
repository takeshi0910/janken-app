package com.example.app.game.room.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.game.room.application.dto.RoomListItemDto;
import com.example.app.game.room.application.dto.RoomRegisterDto;
import com.example.app.game.room.domain.Room;
import com.example.app.game.room.domain.RoomUser;
import com.example.app.game.room.infrastructure.mapper.RoomMapper;
import com.example.app.game.room.infrastructure.repository.RoomRepository;
import com.example.app.game.room.infrastructure.repository.RoomUsersRepository;
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
    private final RoomUsersRepository roomUsersRepository;

    @Override
    public List<RoomListItemDto> selectRoomsByUserId(int userId) {
        return roomMapper.selectRoomsByUserId(userId);
    }

    @Override
    public RoomRegisterDto findById(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException(
                        "Room not found: " + roomId));
        
        List<Integer> userIds = roomUsersRepository.findUserIdsByRoomId(roomId);
        
        RoomRegisterDto dto = new RoomRegisterDto();
        
        dto.setRoomId(room.getRoomId());
        dto.setRoomName(room.getRoomName());
        dto.setGameKind(room.getGameKind());
        dto.setRoundCount(room.getRoundCount());
        dto.setRoomStatus(room.getRoomStatus());
        dto.setStartedDate(room.getStartedDate());
        dto.setEndDate(room.getEndDate());
        dto.setUserIds(userIds);
        
        return dto;
    }

    @Override
    @Transactional
    public void save(RoomForm form) {

        // Room を保存して ID を確定
        Room entity = form.toEntity();
        Room saved = roomRepository.save(entity);
        Integer roomId = saved.getRoomId();

        // room_users を一旦削除（Roomのユーザーを洗い替えする）
        roomUsersRepository.deleteByRoomId(roomId);

        // room_users を再挿入
        List<RoomUser> list = form.getUserIds().stream()
                        .map(userId -> new RoomUser(roomId, userId))
                        .toList();
        
        roomUsersRepository.saveAll(list);
    }
}