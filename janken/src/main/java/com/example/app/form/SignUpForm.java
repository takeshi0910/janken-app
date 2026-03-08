package com.example.app.form;

import lombok.Data;

/**
 * ユーザーアカウントの登録フォームクラス
 */
@Data
public class SignUpForm {
	private String email;
	private String password;
	private String userName;
}


