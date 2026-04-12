package com.example.app.presentation.janken;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.application.janken.JankenApplicationService;
import com.example.app.application.janken.dto.BattleResultDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JankenBattleController {

    private final JankenApplicationService service;

    @GetMapping("/battle/execute")
    public String executeBattle(@RequestParam Integer roomId, Model model) {

        // 対戦実行（勝敗判定を含む）
        service.executeBattle(roomId);
        
        BattleResultDto result = service.getBattleResult(roomId);

        // 結果画面に渡す
        model.addAttribute("result", result);
        model.addAttribute("roomId", roomId);

        // 結果画面へ遷移
        return "janken/battleResult";
    }
    
    @GetMapping("/battle/result")
    public String showBattleResult(@RequestParam Integer roomId, Model model) {

        BattleResultDto result = service.getBattleResult(roomId);

        // 結果画面に渡す
        model.addAttribute("result", result);
        model.addAttribute("roomId", roomId);

        // 結果画面へ遷移
        return "janken/battleResult";
    }
}
