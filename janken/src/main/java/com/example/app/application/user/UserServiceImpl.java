package com.example.app.application.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.domain.user.vo.Email;
import com.example.app.domain.user.vo.HashedPassword;
import com.example.app.domain.user.vo.RawPassword;
import com.example.app.domain.user.vo.UserName;
import com.example.app.infrastructure.user.entity.UserEntity;
import com.example.app.infrastructure.user.repository.UserInfoRepository;
import com.example.app.presentation.auth.SignUpForm;

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
    public Optional<UserEntity> searchUserByEmail(String emailValue) {

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
        UserName userName = new UserName(form.getUserName());

        if (repository.findByUserName(userName).isPresent()) {
            return new RegisterResult(RegisterError.USERNAME_EXISTS);
        }

        RawPassword raw = new RawPassword(form.getPassword());
        HashedPassword hashed = raw.hash(passwordEncoder);
        
        LocalDateTime now = LocalDateTime.now();

        UserEntity userEntity = new UserEntity(
                form.getEmail(),
                form.getUserName(),
                hashed.value(),
                now,
                now,
                false
        );

        UserEntity user = repository.save(userEntity);
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
