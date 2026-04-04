package com.example.app.domain.user.vo;

import com.example.app.domain.common.StringValueObject;

/**
 * ログインユーザーの表示名 VO
 */
public final class UserName implements StringValueObject {

    private final String value;

    public UserName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserName must not be empty.");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("UserName must be 50 characters or less.");
        }
        this.value = value;
    }

    // Hibernate が必要とする JavaBean getter
    public String getValue() {
        return value;
    }

    // あなたの StringValueObject の契約を満たす
    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserName other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
