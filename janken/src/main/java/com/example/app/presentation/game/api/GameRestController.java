package com.example.app.presentation.game.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.game.core.GameKind;
import com.example.app.domain.game.core.GameModeDto;

/**
 * Gameに関連するRestController
 * 
 * @author takeshi.kashiwagi
 */
@RestController
public class GameRestController {

    /**
     * 指定されたゲーム種別に対応するモード一覧を返す API
     *
     * @param gameKindPath ゲーム種別のpath (例: janken, poker )
     * @return ゲームが保持するモードの一覧
     */
    @GetMapping("/api/game-modes")
    public List<GameModeDto> getGameModes(@RequestParam String gameKindPath) {

        GameKind kind = GameKind.fromPath(gameKindPath)
                .orElseThrow(
                        () -> new IllegalArgumentException("Unknown gameKind: " + gameKindPath));

        return kind.modes().stream()
                .map(mode -> new GameModeDto(mode.name(), mode.label()))
                .toList();

    }
}
