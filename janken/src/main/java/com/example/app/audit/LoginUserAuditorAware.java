package com.example.app.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.app.security.MyUserDetails;

/**
 * Spring Security からログイン中のユーザーIDを取得し、
 * Spring Data JPA の監査機能（@CreatedBy / @LastModifiedBy）へ提供する。
 * 
 * <p>このクラスは「現在の操作ユーザー」を特定するための公式エントリポイントであり、
 * AuditorPropagationListener によって AuditorContext へ値が伝播される。</p>
 * 
 * @author takeshi.kashiwagi
 */
@Component
public class LoginUserAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = auth.getPrincipal();

        if (principal instanceof MyUserDetails user) {
            return Optional.of(user.getUserId()); // ← 数値の userId
        }
        return Optional.empty();
    }

}
