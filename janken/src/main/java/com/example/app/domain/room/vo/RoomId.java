package com.example.app.domain.room.vo;

import jakarta.persistence.Column;

import com.example.app.domain.common.IntegerValueObject;

/** RoomId VO*/
public record RoomId(@Column(name = "room_id") Integer value) implements IntegerValueObject {
    public RoomId(String value) { // URL パラメータ(String) 受け取り用
        this(Integer.valueOf(value));
    }
}
