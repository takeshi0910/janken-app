package com.example.app.user.web;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.message.MessageKey;
import com.example.app.message.Messages;
import com.example.app.user.application.UserService;
import com.example.app.user.domain.UserInfo;

import lombok.RequiredArgsConstructor;

/** 
 * ログイン画面Controller
 * 
 * @author 石本晃大
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Messages ｍessages;

    /** 画面表示 */
    @GetMapping("/login")
    public String view(Model model, LoginForm form) {
        return "auth/login";
    }

    /** 
     * ログイン処理
     * 
     * @param form ログイン情報
     * @return 成功時：マイページ画面へ遷移。失敗時：失敗メッセージ付きでログイン画面へ戻る。
     */
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
