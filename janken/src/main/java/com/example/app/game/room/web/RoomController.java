package com.example.app.game.room.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.game.domain.GameKind;
import com.example.app.game.room.application.RoomService;
import com.example.app.game.room.application.dto.RoomRegisterDto;
import com.example.app.user.application.UserService;

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
    private final UserService userService;
    
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("gameKinds", GameKind.values());
    }

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

        if (roomId == null) {       // 新規
            form = new RoomForm();
        } else {        // 編集
            RoomRegisterDto dto = roomService.findById(roomId);
            form = dto.toForm();
        }

        model.addAttribute("roomForm", form);
        return "room/registerform";
    }

    /** 
     * ルームのを登録・編集する。
     * 
     * @param roomId
     */
    @PostMapping("/room/save")
    public String saveRoom(
                    @Validated @ModelAttribute("roomForm") RoomForm form,
                    BindingResult bindingResult,
                    Model model) {

        if (bindingResult.hasErrors()) {
            // セレクトボックス再描画用
            model.addAttribute("gameKinds", GameKind.values());
            return "room/registerform";
        }

        roomService.save(form);

        return "redirect:/mypage";
    }

}
