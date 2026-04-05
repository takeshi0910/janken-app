package com.example.app.domain.user.vo;

/**
 * ハッシュ済みパスワード VO
 * ・必ずハッシュ済みであることを型で保証する
 * ・RawPassword からしか生成されない前提
 */
public record HashedPassword(String value) {

    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("hashed password must not be blank");
        }
        if (!isValidBCrypt(value)) {
            throw new IllegalArgumentException("invalid hashed password format: " + value);
        }
    }

    private static boolean isValidBCrypt(String value) {
        return value.startsWith("$2a$")
            || value.startsWith("$2b$")
            || value.startsWith("$2y$");
    }

    public static HashedPassword from(String value) {
        return new HashedPassword(value);
    }
}
