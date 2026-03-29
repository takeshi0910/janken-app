package com.example.app.room.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.app.room.application.dto.RoomListItemDto;
import com.example.app.room.domain.RoomId;

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
    
    /**
     * ログインユーザーが出し手を登録済みのじゃんけんルームIDを返す。
     * 
     * @param userId ログインユーザーID
     * @param roomIds　プレイヤーの参加しているじゃんけんルームIDのリスト
     * @rerutn　出し手登録済みのじゃんけんルームIDのリスト
     */
    List<RoomId> selectRoomHandRegisteredMap(
            @Param("userId") int userId,
            @Param("roomIds") List<RoomId> roomIds);
}