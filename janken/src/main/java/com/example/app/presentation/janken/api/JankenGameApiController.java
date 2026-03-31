package com.example.app.presentation.janken.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.application.janken.JankenApplicationService;
import com.example.app.domain.room.RoomId;

import lombok.RequiredArgsConstructor;

/**
 * じゃんけんゲームの進行用のController
 * 
 * @author takeshi.kashiwagi
 */
@RestController
@RequiredArgsConstructor
public class JankenGameApiController {

    private final JankenApplicationService jankenService;

    /**
     * じゃんけんバトルを実施するAPI
     * 
     * @param roomId じゃんけんバトルを実施するルームID
     * @return 対戦結果
     */
    @PostMapping("/janken/battle")
    public ResponseEntity<Void> battle(@PathVariable("roomId") RoomId roomId) {
        jankenService.battle(roomId);
        return ResponseEntity.ok().build();
    }
}
