package com.example.app.domain.room;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/** RoomId VO*/
@Embeddable // JPA で VO を PK にする
public record RoomId(@Column(name = "room_id") Integer value) {
    public RoomId(String value) { // URL パラメータ(String) 受け取り用
        this(Integer.valueOf(value));
    }
}
