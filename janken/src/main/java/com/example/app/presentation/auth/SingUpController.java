package com.example.app.presentation.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.application.user.RegisterResult;
import com.example.app.application.user.UserServiceImpl;
import com.example.app.common.message.MessageKey;
import com.example.app.common.message.Messages;

import lombok.RequiredArgsConstructor;

/** 
 * サインアップ画面Controller
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequiredArgsConstructor
public class SingUpController {

    private final UserServiceImpl service;
    private final Messages messages;

    /** 画面表示 */
    @GetMapping("/signUp")
    public String view(Model model, SignUpForm signUpForm) {
        return "auth/signUp";
    }

    /** 
     * サインアップ処理
     * 
     * @param signUpForm 登録するユーザー情報
     * @return 成功時：成功メッセージ付きでログイン画面へ遷移。失敗時：失敗メッセージ付きでサインアップ画面へ戻る。
     *  */
    @PostMapping("/signUp")
    public String userRegister(Model model, SignUpForm signUpForm) {
        RegisterResult result = service.registerUserInfo(signUpForm);
        if (!result.isSuccess()) {
            // エラー内容に対応したメッセージを取得
            MessageKey key = switch (result.getError()) {
                case EMAIL_EXISTS -> MessageKey.REGISTER_EMAIL_EXISTS;
                case USERNAME_EXISTS -> MessageKey.REGISTER_USERNAME_EXISTS;
            };

            model.addAttribute("message", messages.get(key));
            return "auth/signUp";
        }
        
        String message = messages.get(MessageKey.REGISTER_SUCCESS);
        model.addAttribute("successMsg", message);

        // 登録成功 メールアドレスをセットしてログイン画面に遷移させる。
        model.addAttribute("email", signUpForm.getEmail());

        return "auth/login";

    }
}
