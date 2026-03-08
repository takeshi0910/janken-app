package com.example.app.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.form.LoginForm;
import com.example.app.form.SignUpForm;
import com.example.app.message.MessageKey;
import com.example.app.message.Messages;
import com.example.app.service.SignUpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SingUpController {

    private final SignUpService service;
    private final Messages messages;

    @GetMapping("/register")
    public String view(Model model, SignUpForm signUpForm) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(Model model, SignUpForm signUpForm) {
        var userInfo = service.registerUserInfo(signUpForm);
        if (userInfo.isEmpty()) {
            String message = messages.get(MessageKey.REGISTER_ERROR);
            model.addAttribute("message", message);
            return "auth/register";
        } else {
            String message = messages.get(MessageKey.REGISTER_SUCCESS);
            model.addAttribute("successMsg", message);

            // 登録成功 → LoginForm にメールアドレスをセットしてログイン画面に遷移させる。
            LoginForm loginForm = new LoginForm();
            loginForm.setEmail(signUpForm.getEmail());
            model.addAttribute("loginForm", loginForm);

            return "auth/login";
        }
       
    }
}
