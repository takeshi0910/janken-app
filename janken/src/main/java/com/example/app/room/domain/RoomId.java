package com.example.app.room.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/** RoomId */
@Embeddable
public record RoomId(@Column(name = "room_id") Integer value) {
    public RoomId(String value) {
        this(Integer.valueOf(value));
    }
}
