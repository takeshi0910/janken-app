package com.example.app.infrastructure.roomplayer.entity;

import com.example.app.domain.room.RoomId;

import lombok.Getter;
import lombok.Setter;

/**
 * room_usersテーブルのデータ登録用POJO。
 * 
 * entityに変更予定
 * 
 * @author takeshi.kashiwagi
 */
@Getter
@Setter
public class RoomUser {

    private RoomId roomId;
    private Integer userId;

    protected RoomUser() {}

    public RoomUser(RoomId roomId, Integer userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

}

