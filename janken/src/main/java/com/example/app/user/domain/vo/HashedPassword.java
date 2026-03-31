package com.example.app.user.domain.vo;

import java.util.Objects;

/**
 * ハッシュ済みパスワードを表すVO
 * 
 * ・必ずハッシュ済みであることを型で保証する
 * ・DB に保存されるのは常にこの型
 * ・生パスワードは RawPassword 側で扱う
 * 
 * @author takeshi.kashiwagi
 */
public record HashedPassword(String value) {

    private static final String BCRYPT_PREFIX_2A = "$2a$";
    private static final String BCRYPT_PREFIX_2B = "$2b$";
    private static final String BCRYPT_PREFIX_2Y = "$2y$";

    public HashedPassword {
        Objects.requireNonNull(value, "hashed password must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("hashed password must not be blank");
        }

        if (!isValidBCrypt(value)) {
            throw new IllegalArgumentException("invalid hashed password format: " + value);
        }
    }

    private static boolean isValidBCrypt(String value) {
        return value.startsWith(BCRYPT_PREFIX_2A)
            || value.startsWith(BCRYPT_PREFIX_2B)
            || value.startsWith(BCRYPT_PREFIX_2Y);
    }
}