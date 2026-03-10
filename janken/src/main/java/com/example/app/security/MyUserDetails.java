package com.example.app.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.user.domain.UserInfo;

/**
 * SpringSecurity認証用
 * 
 * @author takeshi.kashiwagi
 */
public class MyUserDetails implements UserDetails {

    private final int userId;
    private final String username;
    private final String password;

    public MyUserDetails(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.username = userInfo.getEmail(); // SpringSecurityのusernameに、Eメールをセット
        this.password = userInfo.getPasswordHashed();
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 権限がない場合は空リストでOK
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 常に有効として扱う
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 常にロックされていない
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // パスワードが期限切れでない
        return true;
    }

    @Override
    public boolean isEnabled() {
        // アカウントが有効
        return true;
    }

}