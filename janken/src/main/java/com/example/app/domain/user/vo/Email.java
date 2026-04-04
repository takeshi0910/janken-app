package com.example.app.domain.user.vo;

import java.util.regex.Pattern;

import com.example.app.domain.common.StringValueObject;

/**
 * ユーザーアカウントEmail VO
 */
public final class Email implements StringValueObject {

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be empty.");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + value);
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
        if (!(o instanceof Email other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
