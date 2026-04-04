package com.example.app.application.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.domain.user.vo.Email;
import com.example.app.domain.user.vo.HashedPassword;
import com.example.app.domain.user.vo.UserId;
import com.example.app.domain.user.vo.UserName;
import com.example.app.infrastructure.user.entity.UserInfo;


/**
 * SpringSecurity認証用
 * 
 * @author takeshi.kashiwagi
 */
public class MyUserDetails implements UserDetails {

    private final UserId userId;
    private final Email email;
    private final UserName userName;
    private final HashedPassword password;

    public MyUserDetails(UserInfo userInfo) {
        this.userId =  userInfo.getUserId();
        this.email = userInfo.getEmail(); // SpringSecurityのusernameに、Eメールをセット
        this.password = userInfo.getPasswordHashed();
        this.userName  =userInfo.getUserName();
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return email.value();
    }

    @Override
    public String getPassword() {
        return password.value();
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
    
    public UserName userName() {
        return userName;
    }


}