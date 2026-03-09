package com.example.app.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.game.room.application.RoomService;
import com.example.app.game.room.domain.Room;
import com.example.app.security.MyUserDetails;

import lombok.RequiredArgsConstructor;

/**
 * マイページトップ画面コントローラー
 * 
 * @author masatoki.toyama
 */
@Controller
@RequiredArgsConstructor
public class MypageController {

    private final RoomService roomService;

    @GetMapping("/mypage")
    public String showMypage(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        int userId = userDetails.getUserId();

        List<Room> rooms = roomService.findRoomsByUserId(userId);
        model.addAttribute("rooms", rooms);

        return "mypage";
    }

}