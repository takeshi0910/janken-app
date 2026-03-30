package com.example.app.room.domain;

/** PlayerId VO*/
public record PlayerId(Integer value) {
    
    public PlayerId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("PlayerId must be positive");
        }
    }

    public PlayerId(String value) {
        this(Integer.valueOf(value));
    }
    
    public static PlayerId fromUserId(Integer userId) {
        return new PlayerId(userId);
    }

}

