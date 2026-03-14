package com.example.app.game.room.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * room_usersテーブルに対応したエンティティ
 * 
 * @author takeshi.kashiwagi
 */
@Entity
@Table(name = "room_users")
public class RoomUser {

    @EmbeddedId
    private RoomUserId id;

    protected RoomUser() {}

    public RoomUser(Integer roomId, Integer userId) {
        this.id = new RoomUserId(roomId, userId);
    }

}

