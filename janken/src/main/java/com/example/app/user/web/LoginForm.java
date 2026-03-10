package com.example.app.user.web;

import lombok.Data;

/**
 * ログイン情報を保持するフォームクラス
 * 
 * @author takeshi.kashiwagi
 */
@Data
public class LoginForm {
  private String email;
  private String password;
}
