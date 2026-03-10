package com.example.app.game.room.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.app.game.room.domain.RoomListItemDto;

/**
 * ルームに関連するマッパーインターフェース
 * 
 * @author takeshi.kashiwagi
 */
@Mapper
public interface RoomMapper {
    /**
     * マイページでの表示するルーム情報を取得
     * 
     * @param userId
     * @return 対象者のマイページでの表示するルーム情報のリスト
     */
    List<RoomListItemDto> selectRoomsByUserId(@Param("userId") int userId);
}