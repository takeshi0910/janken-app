package com.example.app.controller.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.entity.UserInfo;
import com.example.app.form.LoginForm;
import com.example.app.message.MessageKey;
import com.example.app.message.Messages;
import com.example.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Messages ｍessages;

    @GetMapping("/login")
    public String view(Model model, LoginForm form) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        Optional<UserInfo> userInfo = userService.searchUserByEmail(form.getEmail());
        boolean isCorrectUserAuth = userInfo.isPresent()
                        && passwordEncoder.matches(form.getPassword(),
                                        userInfo.get().getPasswordHashed());
        if (isCorrectUserAuth) {
            return "redirect:/mypage";
        } else {
            var errorMsg = ｍessages.get(MessageKey.LOGIN_WRONG_INPUT);
            model.addAttribute("errorMsg", errorMsg);
            return "auth/login";
        }
    }
}
