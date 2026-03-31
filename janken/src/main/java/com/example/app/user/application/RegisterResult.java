package com.example.app.user.application;


import com.example.app.user.infrastructure.entity.UserInfo;

import lombok.Getter;

/**
 * ユーザー登録結果を保持する
 * 
 * @author takeshi.kashiwagi
 */
@Getter
public class RegisterResult {
    
    private final UserInfo userInfo;
    private final RegisterError error;

    public RegisterResult(UserInfo userInfo) {
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
