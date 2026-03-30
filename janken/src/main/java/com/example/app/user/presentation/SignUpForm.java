package com.example.app.user.presentation;

import lombok.Data;

/**
 * サインアップ用（ユーザーアカウントの登録）フォームクラス
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class SignUpForm {
    private String email;
    private String password;
    private String userName;
}
