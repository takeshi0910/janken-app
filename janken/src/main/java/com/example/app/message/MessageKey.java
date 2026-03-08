package com.example.app.message;

/** 
 * messages.propertiesのキー定義
 */
public enum MessageKey {
	// ログイン
    LOGIN_WRONG_INPUT("login.wrongInput"),

    // サインアップ
    REGISTER_EMAIL_EXISTS("register.emailExists"),
    REGISTER_USERNAME_EXISTS("register.usernameExists"),
    REGISTER_SUCCESS("register.success");
    
    private final String key;

    MessageKey(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
