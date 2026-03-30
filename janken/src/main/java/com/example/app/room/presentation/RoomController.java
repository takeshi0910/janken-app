package com.example.app.room.presentation;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.game.core.GameKind;
import com.example.app.game.core.GameMode;
import com.example.app.room.application.RoomService;
import com.example.app.room.application.dto.RoomRegisterDto;
import com.example.app.room.domain.RoomId;
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
    @GetMapping("/room/registerRoomForm")
    public String showRoomForm(
                    @RequestParam(value = "roomId", required = false) Integer  roomIdValue,
                    Model model) {

        RoomId roomId = (roomIdValue != null) ? new RoomId(roomIdValue) : null;

        RoomForm form;
        List<? extends GameMode> gameModes;

        if (roomId == null) {       // 新規
            form = new RoomForm();
            gameModes = Collections.emptyList();
        } else {        // 編集
            RoomRegisterDto dto = roomService.findById(roomId);
            form = dto.toForm();
            gameModes = form.getGameKind().modes();
        }

        model.addAttribute("roomForm", form);
        model.addAttribute("gameModes", gameModes);
        
        return "room/registerRoomForm";
    }

    /** 
     * ルーム情報を登録・更新する。
     * 
     * @param roomId
     */
    @PostMapping("/room/save")
    public String saveRoom(
                    @Validated @ModelAttribute("roomForm") RoomForm form,
                    BindingResult bindingResult,
                    Model model,
                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // セレクトボックス再描画用
            List<? extends GameMode> gameModes = form.getGameKind().modes();
            model.addAttribute("gameModes", gameModes);
            return "room/registerRoomForm";
        }

        roomService.save(form);
        
        if(form.getRoomId() == null) {
            redirectAttributes.addFlashAttribute("roomRegisterdMessage",  "ルーム：" + form.getRoomName() + " を登録しました。");
        } else {
            redirectAttributes.addFlashAttribute("roomRegisterdMessage", "ルーム：" + form.getRoomName() + " を更新しました。");
        }

        return "redirect:/mypage";
    }

}
