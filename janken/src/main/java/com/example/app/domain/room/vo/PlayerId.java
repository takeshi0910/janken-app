package com.example.app.domain.room.vo;

/** PlayerId VO*/
public record PlayerId(Integer value) {

    public PlayerId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("PlayerId must be positive.");
        }
    }
    
    public static PlayerId from(Integer value) {
        return new PlayerId(value);
    }

    public static PlayerId from(String value) {
        return new PlayerId(Integer.valueOf(value));
    }
}

