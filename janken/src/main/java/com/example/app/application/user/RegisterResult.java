package com.example.app.application.user;


import com.example.app.infrastructure.user.entity.UserEntity;

import lombok.Getter;

/**
 * ユーザー登録結果を保持する
 * 
 * @author takeshi.kashiwagi
 */
@Getter
public class RegisterResult {
    
    private final UserEntity userInfo;
    private final RegisterError error;

    public RegisterResult(UserEntity userInfo) {
        this.userInfo = userInfo;
        this.error = null;
    }

    public RegisterResult(RegisterError error) {
        this.userInfo = null;
        this.error = error;
    }

    public boolean isSuccess() {
        return userInfo != null;
    }
}
