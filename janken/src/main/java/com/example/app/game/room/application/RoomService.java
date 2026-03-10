package com.example.app.game.room.application;

import java.util.List;

import com.example.app.game.room.domain.Room;
import com.example.app.game.room.domain.RoomListItemDto;

/**
 * ROOMに関連するサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface RoomService {

    /**
     * マイページでの表示するルーム情報を取得
     * 
     * @param userId
     * @return 対象者のマイページでの表示するルーム情報のリスト
     */
    List<RoomListItemDto> selectRoomsByUserId(int userId);

    /**
         * ルーム情報の登録
         * 
         * @param room 登録するルーム情報
         * @param userId セッションID
         * @return 登録したルームオブジェクト
         */
    public Room createRoom(Room room, int userId);

    /**
       * ルーム情報の更新
       * 
       * @param room 更新するルーム情報
       * @param userId セッションID
       * @return 更新したルームオブジェクト
       */
    public Room updateRoom(Room room, int userId);

}