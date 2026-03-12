package com.example.app.game.room.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.game.domain.GameKind;
import com.example.app.game.room.application.RoomService;
import com.example.app.game.room.domain.Room;

import lombok.RequiredArgsConstructor;

/** 
 * ルームに関連するController
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /** 
     * ルームの登録・編集画面を表示する。
     * 
     * @param roomId
     */
    @GetMapping("/room/registerform")
    public String showRoomForm(
                    @RequestParam(value = "roomId", required = false) Integer roomId,
                    Model model) {

        RoomForm form;

        if (roomId == null) {
            // 新規作成
            form = new RoomForm();
        } else {
            // 編集
            Room room = roomService.findById(roomId);
            // entity -> form
            form = room.toForm();
        }

        model.addAttribute("gameKinds",  GameKind.values());
        model.addAttribute("roomForm", form);
        return "room/registerform";
    }

}
