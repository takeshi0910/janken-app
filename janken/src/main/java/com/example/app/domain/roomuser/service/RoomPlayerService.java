package com.example.app.domain.roomuser.service;

import java.util.List;
import java.util.Set;

import com.example.app.domain.room.PlayerId;
import com.example.app.domain.room.RoomId;
import com.example.app.infrastructure.roomplayer.entity.RoomUser;

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
