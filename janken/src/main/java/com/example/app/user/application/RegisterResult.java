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
    /** 
     * 成功の可否だけ取得するならBoolean型でもよいが、
     * 今後の拡張（サインアップ後の自動ログインやメール送信等）を視野に入れて、フィールドに保持しておく。
     */
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
