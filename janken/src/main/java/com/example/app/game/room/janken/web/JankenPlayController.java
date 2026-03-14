package com.example.app.game.room.janken.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.game.room.application.RoomService;
import com.example.app.game.room.application.dto.RoomRegisterDto;

import lombok.RequiredArgsConstructor;

/** 
 * じゃんけんのプレイ画面に関連するController
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequiredArgsConstructor
public class JankenPlayController {
    
    private final RoomService roomService;

    /** 
     * じゃんけんのプレイ画面を表示する。
     * 
     * @param roomId
     */
    @GetMapping("/room/janken/play")
    public String showRoomForm(
                    @RequestParam(value = "roomId") Integer roomId,
                    Model model) {

        // Room を取得
        RoomRegisterDto room = roomService.findById(roomId);

        // 必要な情報を model に詰める
        model.addAttribute("roomId", roomId);
        model.addAttribute("roundCount", room.getRoundCount());

        return "room/janken/jankenPlay";
    }
}
