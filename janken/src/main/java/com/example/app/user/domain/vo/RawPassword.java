package com.example.app.user.domain.vo;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 生パスワードを表すVO
 *
 * ・生パスワードはこの型にしか存在しない
 * ・バリデーションをここで完結させる
 * ・ハッシュ化して HashedPassword を生成する
 * 
 * @author takeshi.kashiwagi
 */
public record RawPassword(String value) {

    private static final int MIN_LENGTH = 8;

    public RawPassword {
        Objects.requireNonNull(value, "raw password must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("raw password must not be blank");
        }

        if (value.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("password must be at least " + MIN_LENGTH + " characters");
        }
    }

    /**
     * パスワードをハッシュ化して HashedPassword を生成する
     */
    public HashedPassword hash(PasswordEncoder encoder) {
        String hashed = encoder.encode(value);
        return new HashedPassword(hashed);
    }
}
