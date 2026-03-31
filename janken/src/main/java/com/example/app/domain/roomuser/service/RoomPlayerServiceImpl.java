package com.example.app.domain.roomuser.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.app.domain.room.PlayerId;
import com.example.app.domain.room.RoomId;
import com.example.app.infrastructure.roomplayer.entity.RoomUser;
import com.example.app.infrastructure.roomplayer.mapper.RoomUserMapper;

import lombok.RequiredArgsConstructor;

/**
 * ルームユーザーに関連するサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class RoomPlayerServiceImpl implements  RoomPlayerService{
    
    private final RoomUserMapper roomUserMapper;
    
    @Override
    public Set<PlayerId> findPlayerIdsByRoomId(RoomId roomId) {
        return roomUserMapper.selectUserIdsByRoomId(roomId);
    }
    
    @Override
    public void deleteByRoomId(RoomId roomId) {
        roomUserMapper.deleteByRoomId(roomId);
    }

    @Override
    public void insertRoomPlayerIds(List<RoomUser> list) {
        roomUserMapper.insertRoomUsers(list);
    }
}
