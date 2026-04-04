package com.example.app.domain.user;

/** 
 * ログインユーザーID VO
 * 
 * @author takeshi.kashiwagi
 */
public record UserId(int value) {

    public UserId {
        if (value <= 0) {
            throw new IllegalArgumentException("UserId must be positive.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
