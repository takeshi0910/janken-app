package com.example.app.application.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginUserProvider {

    public Integer getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("ログインユーザーが取得できません");
        }

        Object principal = auth.getPrincipal();

        if (principal instanceof MyUserDetails user) {
            return user.getUserId().value();
        }

        throw new IllegalStateException("ログインユーザーが取得できません");
    }
}
