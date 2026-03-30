package com.example.app.room.roomuser.application;

import java.util.List;
import java.util.Set;

import com.example.app.room.domain.PlayerId;
import com.example.app.room.domain.RoomId;
import com.example.app.room.roomuser.domain.RoomUser;

/**
 * ルームユーザーに関連するサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface RoomPlayerService {

    /**
     * 指定した roomId に紐づく全ての PlayerId のリストを返す。
     * 
     * @param roomId
     * @return PlayerIdのリスト
     */
    public Set<PlayerId> findPlayerIdsByRoomId(RoomId roomId);

    /**
     * 指定した roomId に紐づく全ての RoomPlayer レコードを削除する。
     * 
     * @param roomId
     */
    public void deleteByRoomId(RoomId roomId);

    /**
     * RoomPlayerを一括で登録する。
     * 
     * @param list RoomPlayer登録用POJOのリスト
     */
    public void insertRoomPlayerIds(List<RoomUser> list);

}
