package com.example.app.room.application;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.room.application.dto.RoomListItemDto;
import com.example.app.room.application.dto.RoomRegisterDto;
import com.example.app.room.domain.Room;
import com.example.app.room.infrastructure.mapper.RoomMapper;
import com.example.app.room.infrastructure.repository.RoomRepository;
import com.example.app.room.roomuser.application.RoomUserService;
import com.example.app.room.roomuser.domain.RoomUser;
import com.example.app.room.web.RoomForm;

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
    private final RoomUserService roomUserService;

    @Override
    public List<RoomListItemDto> selectRoomsByUserId(int userId) {
        // ① まずルーム一覧を取得（DB 由来）
        List<RoomListItemDto> rooms = roomMapper.selectRoomsByUserId(userId);

        if (rooms.isEmpty()) {
            return rooms;
        }

        // ② ルームID一覧を抽出
        List<Integer> roomIds = rooms.stream()
                .map(RoomListItemDto::getRoomId)
                .toList();

        // ③ じゃんけんルームについて、自分の登録済みのルームを取得
        List<Integer> registeredRoomIds = roomMapper.selectRoomHandRegisteredMap(userId,
                roomIds);

        // ④ じゃんけんルームの出し手情報の登録状況　3 値（true / false / null ※じゃんけん以外）をセット。
        rooms.forEach(room -> {
            Boolean registered = null;

            // じゃんけんだけ登録状況を判定する
            if ("じゃんけん".equals(room.getGameKind())) {
                registered = registeredRoomIds.contains(room.getRoomId());
            }

            room.setIsHandRegistered(registered);
        });

        return rooms;
    }

    @Override
    public RoomRegisterDto findById(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException(
                "Room not found: " + roomId));

        Set<Integer> userIds = roomUserService.findUserIdsByRoomId(roomId);

        RoomRegisterDto dto = new RoomRegisterDto();

        dto.setRoomId(room.getRoomId());
        dto.setRoomName(room.getRoomName());
        dto.setGameKind(room.getGameKind());
        dto.setGameMode(room.getGameMode());
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

        Room entity;

        if (form.getRoomId() == null) {
            // 新規
            entity = form.toNewEntity();
        } else {
            // 編集
            entity = roomRepository.findById(form.getRoomId())
                    .orElseThrow();

            entity.setRoomName(form.getRoomName());
            entity.setGameKind(form.getGameKind());
            entity.setGameMode(form.getGameMode());
            entity.setRoundCount(form.getRoundCount());
            entity.setRoomStatus(form.getRoomStatus());
            entity.setStartedDate(form.getStartedDate());
            entity.setEndDate(form.getEndDate());
        }

        // Room を保存して ID を確定
        Room saved = roomRepository.save(entity);
        Integer roomId = saved.getRoomId();

        // room_users を洗い替え
        roomUserService.deleteByRoomId(roomId);

        // room_users を再挿入
        List<RoomUser> list = form.getUserIds().stream()
                .map(userId -> new RoomUser(roomId, userId))
                .toList();

        roomUserService.insertRoomUsers(list);
    }
    

}