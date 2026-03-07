package com.example.demo.controller.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.message.MessageKey;
import com.example.demo.message.Messages;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Messages ｍessages;

    @GetMapping("/login")
    public String view(Model model, LoginForm form) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        var userInfo = userService.searchUserByEmail(form.getEmail());
        var isCorrectUserAuth = userInfo.isPresent()
                        && passwordEncoder.matches(form.getPassword(),
                                        userInfo.get().getPasswordHashed());
        if (isCorrectUserAuth) {
            return "redirect:/mypage";
        } else {
            var errorMsg = ｍessages.get(MessageKey.LOGIN_WRONG_INPUT);
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }
    }
}
