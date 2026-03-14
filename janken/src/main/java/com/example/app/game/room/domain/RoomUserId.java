package com.example.app.game.room.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/** 
 * room_usersの複合キーに対応したIDクラス
 * 
 * @author takeshi.kashiwagi
 */
@Embeddable
public class RoomUserId implements Serializable {
    private Integer roomId;
    private Integer userId;
    
    public RoomUserId() {}

    public RoomUserId(Integer roomId, Integer userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomUserId)) return false;
        RoomUserId that = (RoomUserId) o;
        return Objects.equals(roomId, that.roomId)
                && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId);
    }

}
