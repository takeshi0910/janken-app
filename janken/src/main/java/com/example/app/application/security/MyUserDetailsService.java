package com.example.app.application.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.infrastructure.user.entity.UserEntity;
import com.example.app.infrastructure.user.jpa.UserJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * SpringSecurity認証用サービス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String emailValue) throws UsernameNotFoundException {

        UserEntity userEntity = userJpaRepository.findByEmail(emailValue)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
        return new MyUserDetails(userEntity);
    }
}