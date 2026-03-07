package com.example.demo.message;

/** 
 * messages.propertiesのキー定義
 */
public enum MessageKey {
	
	/** ログイン画面：入力内容誤り */
    LOGIN_WRONG_INPUT("login.wrongInput"),
	
	/** アカウント作成画面：入力誤り */
    REGISTER_ERROR("register.error"),

	/** アカウント作成画面；登録成功 */
    REGISTER_SUCCESS("register.success");
    
    private final String key;

    MessageKey(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
