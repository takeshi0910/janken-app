package com.example.app.user.application;

import java.util.List;
import java.util.Optional;

import com.example.app.user.domain.UserInfo;
import com.example.app.user.web.SignUpForm;

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
    public Optional<UserInfo> searchUserByEmail(String email);

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
