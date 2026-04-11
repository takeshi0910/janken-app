package com.example.app.application.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.domain.user.vo.HashedPassword;
import com.example.app.domain.user.vo.RawPassword;
import com.example.app.infrastructure.user.entity.UserEntity;
import com.example.app.infrastructure.user.jpa.UserJpaRepository;
import com.example.app.presentation.auth.SignUpForm;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報の参照・登録のサービス実装クラス
 * 
 * @author takeshi.kashiwagi
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserEntity> searchUserByEmail(String emailValue) {

        return userJpaRepository.findByEmail(emailValue);
    }

    @Override
    public RegisterResult registerUserInfo(SignUpForm form) {

        // メールアドレス重複NG
        if (emailExists(form.getEmail())) {
            return new RegisterResult(RegisterError.EMAIL_EXISTS);
        }

        // 名前重複NG
        if (userNameExists(form.getUserName())) {
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

        UserEntity user = userJpaRepository.save(userEntity);
        return new RegisterResult(user);
    }
    
    /**
     * email 重複チェック
     * 
     * @param email Email文字列
     * @return 既存データがあればtrue 、無ければfalse 
     */
    private boolean emailExists(String email) {
        return userJpaRepository.findByEmail(email).isPresent();
    }

    /**
     * ユーザー名 重複チェック
     * 
     * @param userName ユーザー名文字列
     * @return 既存データがあればtrue 、無ければfalse 
     */
    private boolean userNameExists(String userName) {
        return userJpaRepository.findByUserName(userName).isPresent();
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> list = userJpaRepository.findAll()
                        .stream()
                        .map(UserDto::from)
                        .toList();
        System.out.println("DEBUG users = " + list);
        return list;
    }
}
