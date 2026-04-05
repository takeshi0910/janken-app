package com.example.app.domain.user.vo;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 生パスワード VO
 * ・生パスワードはこの型にしか存在しない
 * ・バリデーションをここで完結させる
 * ・ハッシュ化して HashedPassword を生成する
 */
public record RawPassword(String value) {

    private static final int MIN_LENGTH = 8;

    public RawPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("raw password must not be blank");
        }
        if (value.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("password must be at least " + MIN_LENGTH + " characters");
        }
    }

    public static RawPassword fromString(String value) {
        return new RawPassword(value);
    }

    public HashedPassword hash(PasswordEncoder encoder) {
        return new HashedPassword(encoder.encode(value));
    }
}