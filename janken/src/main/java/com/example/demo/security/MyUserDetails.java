package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserInfo;

/**
 * @author masatoki.toyama
 */
public class MyUserDetails implements UserDetails {

    private final UserInfo userInfo;

    public MyUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }

    @Override
    public String getPassword() {
        return userInfo.getPasswordHashed();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 権限がない場合は空リストでOK
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 常に有効として扱う
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 常にロックされていない
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // パスワードが期限切れでない
    }

    @Override
    public boolean isEnabled() {
        return true; // アカウントが有効
    }

    public UserInfo getUser() {
        return this.userInfo;
    }
}