package com.example.app.domain.user.vo;

/**
 * ログインユーザーID VO
 */
public record UserId(Integer value) {

    public UserId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("UserId must be positive.");
        }
    }

    public static UserId from(Integer value) {
        return new UserId(value);
    }
    
    public static UserId from(String value) {
        return new UserId(Integer.valueOf(value));
    }
}