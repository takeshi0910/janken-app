package com.example.app.presentation.janken;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.application.janken.JankenApplicationService;
import com.example.app.application.janken.dto.JankenRegisteredStatusDto;

import lombok.RequiredArgsConstructor;

/** 
 * じゃんけんの対戦ボタン押下時の、出し手の登録状況（登録済みor未登録）のモーダル描画用Controller
 * 
 * @author takeshi.kashiwagi
 */
@Controller
@RequiredArgsConstructor
public class JankenRegisteredStatusController {
    
    private final JankenApplicationService service;
    
    @GetMapping("/janken/registeredStatus")
    public String jankenStatus(@RequestParam Integer roomId, Model model) {

        JankenRegisteredStatusDto dto = service.getJankenRegisterdStatus(roomId);

        model.addAttribute("jankenRegisterdStatus", dto);
        model.addAttribute("roomId", roomId);

        return "mypage/jankenStatus :: content";
    }
}
