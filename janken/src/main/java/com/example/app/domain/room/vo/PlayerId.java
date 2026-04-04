package com.example.app.domain.room.vo;

import jakarta.persistence.Embeddable;

import com.example.app.domain.common.IntegerValueObject;

/** PlayerId VO*/
@Embeddable
public record PlayerId(Integer value) implements IntegerValueObject {
    
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

