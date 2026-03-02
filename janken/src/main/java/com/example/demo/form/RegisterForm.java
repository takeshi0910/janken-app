package com.example.demo.form;

import lombok.Data;

@Data
public class RegisterForm {
	
	private String loginId;
	
	private String password;
	
	private String userName;
	
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}


