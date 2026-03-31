package com.example.app.user.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.user.domain.vo.Email;
import com.example.app.user.domain.vo.HashedPassword;
import com.example.app.user.domain.vo.RawPassword;
import com.example.app.user.domain.vo.UserName;
import com.example.app.user.infrastructure.entity.UserInfo;
import com.example.app.user.infrastructure.repository.UserInfoRepository;
import com.example.app.user.presentation.SignUpForm;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報の参照・登録に関するコントローラー
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserInfo> searchUserByEmail(String emailValue) {

        // Strimg -> Email VO変換
        Email email = new Email(emailValue);

        return repository.findByEmail(email);
    }

    @Override
    public RegisterResult registerUserInfo(SignUpForm form) {

        // メールアドレス重複NG
        if (repository.findByEmail(new Email(form.getEmail())).isPresent()) {
            return new RegisterResult(RegisterError.EMAIL_EXISTS);
        }

        // 名前重複NG
        if (repository.findByUserName(form.getUserName()).isPresent()) {
            return new RegisterResult(RegisterError.USERNAME_EXISTS);
        }

        UserInfo userInfo = new UserInfo();

        // パスワードは、SpringSecurityにより、エンコード（実際はハッシュ化）した値を格納する。
        RawPassword raw = new RawPassword(form.getPassword());
        HashedPassword hashed = raw.hash(passwordEncoder);

        userInfo.setEmail(new Email(form.getEmail()));
        userInfo.setPasswordHashed(hashed);
        userInfo.setUserName(new UserName(form.getUserName()));
        userInfo.setCreateAt(LocalDateTime.now());
        userInfo.setUpdatedAt(LocalDateTime.now());

        UserInfo user = repository.save(userInfo);
        return new RegisterResult(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> list = repository.findAll()
                        .stream()
                        .map(UserDto::from)
                        .toList();
        System.out.println("DEBUG users = " + list);
        return list;
    }

}
