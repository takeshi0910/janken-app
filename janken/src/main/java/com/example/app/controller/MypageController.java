package com.example.app.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.game.room.application.RoomService;
import com.example.app.game.room.domain.RoomListItemDto;
import com.example.app.security.MyUserDetails;

import lombok.RequiredArgsConstructor;


/**
 * @author masatoki.toyama
 */
@Controller
@RequiredArgsConstructor
public class MypageController {

    private final RoomService roomService;

    @GetMapping("/mypage")
    public String showMypage(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        // ログイン中ユーザーのIDを取得
        int loginUserId = userDetails.getUserId();

        // 対象ユーザーのルーム情報を取得
        List<RoomListItemDto> rooms = roomService.selectRoomsByUserId(loginUserId);

        model.addAttribute("loginUserId", loginUserId);
        model.addAttribute("rooms", rooms);
        
        return "mypage";
    }
    




}