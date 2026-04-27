package com.example.app.domain.user.vo;

/**
 * ログインユーザーの表示名 VO
 */
public record UserName(String value) {

    public UserName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserName must not be empty.");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("UserName must be 50 characters or less.");
        }
    }

    public static UserName from(String value) {
        return new UserName(value);
    }
}
