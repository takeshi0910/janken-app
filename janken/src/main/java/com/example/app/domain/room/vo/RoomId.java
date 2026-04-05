package com.example.app.domain.room.vo;

/** RoomId VO*/
public record RoomId(Integer value) {

    public RoomId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("RoomId must be positive.");
        }
    }

    public static RoomId from(Integer value) {
        return new RoomId(value);
    }
    
    public static RoomId from(String value) {
        return new RoomId(Integer.valueOf(value));
    }
}