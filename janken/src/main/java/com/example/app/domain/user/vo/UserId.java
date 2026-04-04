package com.example.app.domain.user.vo;

import com.example.app.domain.common.IntegerValueObject;

/** 
 * ログインユーザーID VO
 * 
 * @author takeshi.kashiwagi
 */
public final class UserId implements IntegerValueObject {

    private final Integer value;

    public UserId(Integer value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("UserId must be positive.");
        }
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserId other))
            return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
