package com.example.app.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.message.MessageKey;
import com.example.app.message.Messages;

import lombok.RequiredArgsConstructor;

/** 
 * ログイン画面Controller
 * 
 * @author 柏木 健
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final Messages ｍessages;
    
    /** 画面表示 */
    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        if (error != null) {
            var errorMsg = ｍessages.get(MessageKey.LOGIN_WRONG_INPUT);
            model.addAttribute("errorMsg", errorMsg);
        }

        return "auth/login";
    }
}
