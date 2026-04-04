package com.example.app.domain.user.vo;

import java.util.Objects;

import com.example.app.domain.common.StringValueObject;

/**
 * ハッシュ済みパスワードを表すVO
 * 
 * ・必ずハッシュ済みであることを型で保証する
 * ・DB に保存されるのは常にこの型
 * ・生パスワードは RawPassword 側で扱う
 * 
 * @author takeshi.kashiwagi
 */
public final class HashedPassword implements StringValueObject {

    private final String value;

    public HashedPassword(String value) {
        Objects.requireNonNull(value, "hashed password must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("hashed password must not be blank");
        }

        if (!isValidBCrypt(value)) {
            throw new IllegalArgumentException("invalid hashed password format: " + value);
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

    private static boolean isValidBCrypt(String value) {
        return value.startsWith("$2a$")
                || value.startsWith("$2b$")
                || value.startsWith("$2y$");
    }

    @Override
    public String toString() {
        return value;
    }
}