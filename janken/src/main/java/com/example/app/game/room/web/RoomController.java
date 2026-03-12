package com.example.app.game.room.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** 
 * ルームに関連するController
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

    @GetMapping("/rooms/form")
    public String showRoomForm(
                    @RequestParam(value = "roomId", required = false) Long roomId,
                    Model model) {

        RoomForm form;

        if (roomId == null) {
            // 新規作成
            form = new RoomForm();
        } else {
            // 編集
       //     Room room = roomService.findById(roomId);
       //    form = RoomForm.from(room);
        }

   //     model.addAttribute("roomForm", form);
        return "rooms/form";

        
    }

}
