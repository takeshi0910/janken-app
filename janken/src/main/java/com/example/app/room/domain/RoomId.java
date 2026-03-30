package com.example.app.room.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/** RoomId VO*/
@Embeddable // JPA で VO を PK にする
public record RoomId(@Column(name = "room_id") Integer value) {
    public RoomId(String value) { // URL パラメータを String で受け取り用
        this(Integer.valueOf(value));
    }
}
