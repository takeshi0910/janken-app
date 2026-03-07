package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserRegisterForm;
import com.example.demo.message.MessageKey;
import com.example.demo.message.Messages;
import com.example.demo.service.UserRegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserRegisterService service;
    private final Messages ｍessages;

    @GetMapping("/register")
    public String view(Model model, UserRegisterForm userRegisterForm) {
        return "register";
    }

    @PostMapping("/register")
    public void register(Model model, UserRegisterForm form) {
        var userInfo = service.registerUserInfo(form);
        if (userInfo.isEmpty()) {
            var message = ｍessages.get(MessageKey.REGISTER_ERROR);
            model.addAttribute("message", message);
        } else {
            var message = ｍessages.get(MessageKey.REGISTER_SUCCESS);
            model.addAttribute("message", message);
        }

    }
}
