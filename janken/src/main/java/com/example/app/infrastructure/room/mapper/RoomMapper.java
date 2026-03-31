package com.example.app.infrastructure.room.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.app.domain.room.RoomId;
import com.example.app.domain.room.application.dto.RoomListItemDto;
import com.example.app.user.domain.vo.UserId;

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
     * @param userId ログインユーザーID
     * @return 対象者のマイページでの表示するルーム情報のリスト
     */
    List<RoomListItemDto> selectRoomsByUserId(@Param("userId") UserId userId);
    
    /**
     * ログインユーザーが出し手を登録済みのじゃんけんルームIDを返す。
     * 
     * @param userId ログインユーザーID
     * @param roomIds　プレイヤーの参加しているじゃんけんルームIDのリスト
     * @rerutn　出し手登録済みのじゃんけんルームIDのリスト
     */
    List<RoomId> selectRoomHandRegisteredMap(
            @Param("userId") UserId userId,
            @Param("roomIds") List<RoomId> roomIds);
}