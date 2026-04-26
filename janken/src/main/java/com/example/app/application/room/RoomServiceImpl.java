package com.example.app.application.room;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.application.room.dto.RoomListItemDto;
import com.example.app.application.room.dto.RoomRegisterDto;
import com.example.app.application.security.LoginUserProvider;
import com.example.app.domain.room.RoomStatus;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.domain.user.vo.UserId;
import com.example.app.infrastructure.room.entity.RoomEntity;
import com.example.app.infrastructure.room.mapper.RoomMapper;
import com.example.app.infrastructure.room.repository.RoomJpaRepository;
import com.example.app.infrastructure.roomplayer.entity.RoomPlayerEntity;
import com.example.app.infrastructure.roomplayer.repository.RoomPlayerJpaRepository;
import com.example.app.presentation.room.RoomForm;

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
    private final RoomJpaRepository roomJpaRepository;
    private final RoomPlayerJpaRepository roomPlayerJpaRepository;
    private final LoginUserProvider loginUserProvider;

    @Override
    public List<RoomListItemDto> selectRoomsByUserId(UserId userId) {
        // ① まずルーム一覧を取得（DB 由来）
        List<RoomListItemDto> rooms = roomMapper.selectRoomsByUserId(userId.value());

        if (rooms.isEmpty()) {
            return rooms;
        }

        // ② ルームID一覧を抽出
        List<Integer> roomIds = rooms.stream()
                .map(room -> room.getRoomId())
                .toList();

        // ③ じゃんけんルームについて、自分の登録済みのルームを取得
        List<Integer> registeredRoomIds = roomMapper.selectRoomHandRegisteredMap(userId.value(),
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
    public RoomRegisterDto findById(RoomId roomId) {
        RoomEntity room = roomJpaRepository.findById(roomId.value())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Room not found: " + roomId));

        Set<Integer> playerIds = roomPlayerJpaRepository.findPlayerIdByRoomId(roomId.value());

        RoomRegisterDto dto = new RoomRegisterDto();

        dto.setRoomId(roomId.value());
        dto.setRoomName(room.getRoomName());
        dto.setGameKind(room.getGameKind());
        dto.setGameMode(room.getGameKind().toMode(room.getGameMode()));
        dto.setRoundCount(room.getRoundCount());
        dto.setRoomStatus(room.getRoomStatus());
        dto.setStartedDate(room.getStartedDate());
        dto.setEndDate(room.getEndDate());
        dto.setPlayerIds(playerIds);

        return dto;
    }

    @Override
    @Transactional
    public void save(RoomForm form) {

        RoomEntity entity;

        if (form.getRoomId() == null) {
            // 新規
            entity = new RoomEntity();
            entity.setRoomId(null); // DB が採番するなら null のまま
        } else {
            // 編集
            entity = roomJpaRepository.findById(form.getRoomId())
                    .orElseThrow();
        }

        // 新規・編集共通の上書き処理
        entity.setRoomName(form.getRoomName());
        entity.setGameKind(form.getGameKind());
        entity.setGameMode(form.getGameMode());
        entity.setRoundCount(form.getRoundCount());
        entity.setRoomStatus(form.getRoomStatus());
        entity.setStartedDate(form.getStartedDate());
        entity.setEndDate(form.getEndDate());

        // Room を保存して ID を確定
        RoomEntity saved = roomJpaRepository.save(entity);
        RoomId roomId = new RoomId(saved.getRoomId());

        // room_players を洗い替え
        roomPlayerJpaRepository.deleteByRoomId(roomId.value());

        // room_players を再挿入
        List<RoomPlayerEntity> list = form.getUserIds().stream()
                .map(userId -> new RoomPlayerEntity(roomId.value(), userId))
                .toList();

        roomPlayerJpaRepository.saveAll(list);
    }

    @Override
    public boolean isRoomMaster(RoomId roomId) {
        Integer loginUserId = loginUserProvider.getUserId();
        RoomEntity room = roomJpaRepository.findById(roomId.value())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Room not found: " + roomId));

        return room.getCreatedId().equals(loginUserId);
    }
    
    @Override
    @Transactional
    public void updateStatus(RoomId roomId, RoomStatus status) {
        roomJpaRepository.updateStatus(roomId.value(), status);
    }

}