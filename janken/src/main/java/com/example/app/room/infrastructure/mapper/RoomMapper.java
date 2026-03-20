package com.example.app.room.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.app.room.application.dto.RoomListItemDto;

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
     * ユーザーが自分が参加しているじゃんけんルームで、出し手を登録しているかをまとめて返す。
     * 
     * @param userId ログインユーザーID
     * @param roomIds　プレイヤーの参加しているじゃんけんルームIDのリスト
     * @rerutn　ルームごとの出し手の登録状況（登録済:true、未登録:false）を表したMap  { roomId: true/false }
     */
    List<Integer> selectRoomHandRegisteredMap(
            @Param("userId") int userId,
            @Param("roomIds") List<Integer> roomIds);
}