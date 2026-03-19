package com.example.app.room.domain;

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

    private Integer roomId;
    private Integer userId;

    protected RoomUser() {}

    public RoomUser(Integer roomId, Integer userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

}

