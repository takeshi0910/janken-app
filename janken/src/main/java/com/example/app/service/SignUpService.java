package com.example.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.entity.UserInfo;
import com.example.app.form.SignUpForm;
import com.example.app.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザーアカウントの登録に関するコントローラー
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserInfo> registerUserInfo(SignUpForm form) {

        if (repository.findByEmail(form.getEmail()).isPresent()) {
            // メールが登録済みだったらNG
            return Optional.empty();
        } else if (repository.findByUserName(form.getUserName()).isPresent()) {
            // 名前が登録済みだったらNG
            return Optional.empty();
        }

        UserInfo userInfo = new UserInfo();
        
        // パスワードは、SpringSecurityにより、エンコード（実際はハッシュ化）した値を格納する。
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        
        userInfo.setEmail(form.getEmail());
        userInfo.setPasswordHashed(encodedPassword);
        userInfo.setUserName(form.getUserName());
        userInfo.setCreateAt(LocalDateTime.now());
        userInfo.setUpdatedAt(LocalDateTime.now());
        
        return Optional.of(repository.save(userInfo));
    }
}
