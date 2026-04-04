package com.example.app.infrastructure.roomplayer.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;

import com.example.app.domain.room.vo.PlayerId;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.infrastructure.roomplayer.entity.RoomUser;

/**
 * ルームユーザーに関連するマッパーインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface RoomUserMapper {

    /**
     * 指定した roomId に紐づく全ての user_id のリストを返す。
     * 
     * @param roomId
     * @return UserIdのリスト
     */
    Set<PlayerId> selectUserIdsByRoomId(@Param("roomId") RoomId roomId);

    /**
     * 指定した roomId に紐づく全ての room_users レコードを削除する。
     * 
     * @param roomId
     */
    void deleteByRoomId(@Param("roomId") RoomId roomId);

    
    /**
     * RoomUserを一括で登録する。
     * 
     * @param list RoomUser登録用POJOのリスト
     */
    void insertRoomUsers(List<RoomUser> list);

}
