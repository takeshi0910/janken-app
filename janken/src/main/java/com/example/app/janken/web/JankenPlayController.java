package com.example.app.janken.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.janken.application.JankenService;
import com.example.app.janken.domain.JankenChoice;
import com.example.app.janken.domain.JankenHand;
import com.example.app.room.application.RoomService;
import com.example.app.room.application.dto.RoomRegisterDto;
import com.example.app.security.MyUserDetails;

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
    private final JankenService jankenService;
    private final AuditorAware<Integer> auditorAware;

    /** 
     * じゃんけんのプレイ画面を表示する。
     * 
     * @param roomId
     */
    @GetMapping("/room/janken/play")
    public String show(
                    @RequestParam(value = "roomId") Integer roomId,
                    Model model,
                    @AuthenticationPrincipal MyUserDetails loginUser) {

        // Room を取得
        RoomRegisterDto room = roomService.findById(roomId);

        // --- ① 既存の選択肢を取得（編集モード対応） ---
        List<JankenChoice> choices = jankenService.getJankenChoices(roomId,
                        loginUser.getUserId());

        // --- ② フォームを作成 ---
        JankenChoiceForm form = new JankenChoiceForm();
        form.setRoomId(roomId);

        // hands の初期化
        int roundCount = room.getRoundCount();
        
        // じゃんけんの出し手と順番の配列を作成する。
        List<JankenChoiceForm.ChoiceRow> rows = new ArrayList<>();

        if (choices.isEmpty()) {
         // 初回表示 → 空の行を roundCount 分作る
            for (int i = 0; i < roundCount; i++) {
                JankenChoiceForm.ChoiceRow row = new JankenChoiceForm.ChoiceRow();
                row.setOrderNo(i + 1);
                row.setHand(null); // 未選択
                rows.add(row);
            }

        } else {
            // 編集表示 → DB の値を順番通りにセット
            for (int i = 0; i < roundCount; i++) {
                final int order = i + 1;

                JankenHand hand = choices.stream()
                        .filter(c -> c.getOrderNo() == order)
                        .map(JankenChoice::getJankenHand)
                        .findFirst()
                        .orElse(null);

                JankenChoiceForm.ChoiceRow row = new JankenChoiceForm.ChoiceRow();
                row.setOrderNo(order);
                row.setHand(hand);

                rows.add(row);
            }
        }

        form.setChoices(rows);

        // --- ③ model に詰める ---
        model.addAttribute("form", form);
        model.addAttribute("roomName", room.getRoomName());
        model.addAttribute("roundCount", roundCount);

        return "janken/jankenPlay";
    }

    /** 
     * じゃんけんの出し手情報を登録する。
     * 
     * @param roomId ルームID
     * @param form 画面入力値
     */
    @PostMapping("/rooms/{roomId}/choices")
    public String saveChoices(
                    @PathVariable Integer roomId,
                    @ModelAttribute JankenChoiceForm form) {
      
        List<JankenChoice> choices = form.getChoices().stream()
                        .map(row -> new JankenChoice(
                                roomId,
                                row.getOrderNo(),
                                null, //  playerId はサービス層でセット
                                row.getHand()
                        ))
                        .toList();

        jankenService.registerJankenChoices(roomId, choices);
        
        return "redirect:/mypage";

    }
}
