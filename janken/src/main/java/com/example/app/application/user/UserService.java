package com.example.app.application.user;

import java.util.List;
import java.util.Optional;

import com.example.app.infrastructure.user.entity.UserEntity;
import com.example.app.presentation.auth.SignUpForm;

/**
 * ユーザー情報の参照・登録のサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface UserService {

    /**
     * メールアドレスからユーザー情報取得
     * 
     * @param email
     * @return ユーザー情報
     */
    public Optional<UserEntity> searchUserByEmail(String emailValue);

    /** 
     * ユーザー登録処理
     * 
     * @param form
     * @return 登録結果
     */
    public RegisterResult registerUserInfo(SignUpForm form);

    /** 
     * 全ユーザー情報取得
     * 
     * @return ユーザー情報のリスト
     */
    public List<UserDto> findAll();
}
