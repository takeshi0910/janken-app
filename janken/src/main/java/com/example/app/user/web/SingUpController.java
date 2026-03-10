package com.example.app.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.message.MessageKey;
import com.example.app.message.Messages;
import com.example.app.user.application.RegisterResult;
import com.example.app.user.application.UserService;

import lombok.RequiredArgsConstructor;

/** 
 * サインアップ画面Controller
 * 
 * @author 石本晃大
 */
@Controller
@RequiredArgsConstructor
public class SingUpController {

    private final UserService service;
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

        // 登録成功 → LoginForm にメールアドレスをセットしてログイン画面に遷移させる。
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(signUpForm.getEmail());
        model.addAttribute("loginForm", loginForm);

        return "auth/login";

    }
}
