package com.example.app.presentation.mypage;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.application.room.RoomService;
import com.example.app.application.room.dto.RoomListItemDto;
import com.example.app.application.security.MyUserDetails;
import com.example.app.domain.user.vo.UserId;

import lombok.RequiredArgsConstructor;

/**
 * マイページ表示用コントローラー
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequiredArgsConstructor
public class MypageController {

    private final RoomService roomService;

    @GetMapping("/mypage")
    public String showMypage(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        // ログイン中ユーザーのIDを渡す
        UserId loginUserId = userDetails.getUserId();
        model.addAttribute("loginUserId", loginUserId);

        // 対象ユーザーのルーム情報を渡す
        List<RoomListItemDto> rooms = roomService.selectRoomsByUserId(loginUserId);
        model.addAttribute("rooms", rooms);
        
        return "mypage/mypage";
    }
    
}