package com.example.app.user.domain.vo;

import java.util.regex.Pattern;

/** 
 * ユーザーアカウントEmail VO
 * 
 * @author takeshi.kashiwagi
 */
public record Email(String value) {

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be empty.");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
