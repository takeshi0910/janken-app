package com.example.app.game.room.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * ルームに関連するController
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {
    
    @GetMapping("/{roomId}")
    public String enterRoom(@PathVariable Integer roomId, Model model) {

      //  RoomDetailDto roomDetail = roomService.getRoomDetail(roomId);

      //  model.addAttribute("room", roomDetail);

        return "room/detail"; // じゃんけん画面
    }

}
