package com.example.app.user.application;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.user.domain.UserInfo;
import com.example.app.user.domain.UserInfoRepository;
import com.example.app.user.web.SignUpForm;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報の参照・登録に関するコントローラー
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    /** メールからユーザー情報取得
     * 
     * @param email
     * @return ユーザー情報
     */
    public Optional<UserInfo> searchUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    /** 
     * サインアップ処理
     * 
     * @param form
     * @return ユーザー情報
     */
    public RegisterResult registerUserInfo(SignUpForm form) {

        // メールアドレス重複NG
        if (repository.findByEmail(form.getEmail()).isPresent()) {
            return new RegisterResult(RegisterError.EMAIL_EXISTS);
        }

        // 名前重複NG
        if (repository.findByUserName(form.getUserName()).isPresent()) {
            return new RegisterResult(RegisterError.USERNAME_EXISTS);
        }

        UserInfo userInfo = new UserInfo();

        // パスワードは、SpringSecurityにより、エンコード（実際はハッシュ化）した値を格納する。
        String encodedPassword = passwordEncoder.encode(form.getPassword());

        userInfo.setEmail(form.getEmail());
        userInfo.setPasswordHashed(encodedPassword);
        userInfo.setUserName(form.getUserName());
        userInfo.setCreateAt(LocalDateTime.now());
        userInfo.setUpdatedAt(LocalDateTime.now());

        UserInfo user = repository.save(userInfo);
        return new RegisterResult(user);
    }
}
