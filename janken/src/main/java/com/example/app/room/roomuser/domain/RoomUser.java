package com.example.app.room.roomuser.domain;

import com.example.app.room.domain.RoomId;

import lombok.Getter;
import lombok.Setter;

/**
 * room_usersテーブルのデータ登録用POJO。
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

