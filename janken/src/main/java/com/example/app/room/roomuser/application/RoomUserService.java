package com.example.app.room.roomuser.application;

import java.util.List;
import java.util.Set;

import com.example.app.room.domain.RoomId;
import com.example.app.room.roomuser.domain.RoomUser;

/**
 * ルームユーザーに関連するサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface RoomUserService {

    /**
     * 指定した roomId に紐づく全ての user_id のリストを返す。
     * 
     * @param roomId
     * @return UserIdのリスト
     */
    public Set<Integer> findUserIdsByRoomId(RoomId roomId);

    /**
     * 指定した roomId に紐づく全ての room_users レコードを削除する。
     * 
     * @param roomId
     */
    public void deleteByRoomId(RoomId roomId);

    /**
     * RoomUserを一括で登録する。
     * 
     * @param list RoomUser登録用POJOのリスト
     */
    public void insertRoomUsers(List<RoomUser> list);

}
