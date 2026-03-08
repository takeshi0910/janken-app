package com.example.app.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.entity.UserInfo;
import com.example.app.model.MypageDTO;
import com.example.app.security.MyUserDetails;
import com.example.app.service.MypageService;


/**
 * @author masatoki.toyama
 */
@Controller
public class MypageController {

    private final MypageService mypageService;

    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping("/mypage")
    public String showMypage(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        UserInfo userInfo = userDetails.getUser();

        List<MypageDTO> myPages = mypageService.getMypageUser(userInfo);
        model.addAttribute("myPages", myPages);
        return "mypage";
    }

}