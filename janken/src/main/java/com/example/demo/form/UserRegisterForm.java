package com.example.demo.form;

import lombok.Data;

/**
 * ユーザーアカウントの登録フォームクラス
 */
@Data
public class UserRegisterForm {
	private String email;
	private String password;
	private String userName;
}


