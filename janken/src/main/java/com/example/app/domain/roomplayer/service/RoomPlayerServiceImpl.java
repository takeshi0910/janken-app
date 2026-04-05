package com.example.app.domain.roomplayer.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.roomplayer.entity.RoomPlayer;
import com.example.app.infrastructure.roomplayer.repository.RoomPlayerRepository;

import lombok.RequiredArgsConstructor;

/**
 * ルームプレイヤーに関連するサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class RoomPlayerServiceImpl implements  RoomPlayerService{
    
    private final RoomPlayerRepository roomPlayerRepository;
    
    @Override
    public Set<PlayerId> findPlayerIdsByRoomId(RoomId roomId) {
        return roomPlayerRepository.findPlayerIdsByRoomId(roomId);
    }
    
    @Override
    public void deleteByRoomId(RoomId roomId) {
        roomPlayerRepository.deleteByRoomId(roomId);
    }

    @Override
    public void saveAll(List<RoomPlayer> roomPlayers) {
        roomPlayerRepository.saveAll(roomPlayers);
    }

  
}
