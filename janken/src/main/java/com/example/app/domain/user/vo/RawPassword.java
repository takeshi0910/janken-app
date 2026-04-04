package com.example.app.domain.user.vo;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.app.domain.common.StringValueObject;

/** 生パスワードを表すVO
 *
 * ・生パスワードはこの型にしか存在しない
 * ・バリデーションをここで完結させる
 * ・ハッシュ化して HashedPassword を生成する
 * 
 * @author takeshi.kashiwagi
 */
public final class RawPassword implements StringValueObject {

    private static final int MIN_LENGTH = 8;
    private final String value;

    public RawPassword(String value) {
        Objects.requireNonNull(value, "raw password must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("raw password must not be blank");
        }

        if (value.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("password must be at least " + MIN_LENGTH + " characters");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String value() {
        return value;
    }

    public HashedPassword hash(PasswordEncoder encoder) {
        return new HashedPassword(encoder.encode(value));
    }
}
