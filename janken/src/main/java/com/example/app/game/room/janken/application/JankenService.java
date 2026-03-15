package com.example.app.game.room.janken.application;

import java.util.List;

import com.example.app.game.room.janken.domain.JankenChoice;

/**
 * じゃんけんゲームのサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface JankenService {
    
    /**
     * プレイヤーの手の登録・更新
     * 
     * @param roomId RoomのID
     * @param roomId プレイヤーの出し手情報
     */
    public void saveChoices(Integer roomId, List<JankenChoice> choices);
}
