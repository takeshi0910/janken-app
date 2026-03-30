package com.example.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.user.domain.vo.Email;
import com.example.app.user.infrastructure.entity.UserInfo;
import com.example.app.user.infrastructure.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * SpringSecurity認証用サービス
 * 
 * @author 柏木 健
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailValue) throws UsernameNotFoundException {

        // Strimg -> Email VO変換
        Email email = new Email(emailValue);

        UserInfo userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
        return new MyUserDetails(userInfo);
    }
}