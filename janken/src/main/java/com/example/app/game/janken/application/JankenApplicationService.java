package com.example.app.game.janken.application;

import java.util.List;

import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;

/**
 * じゃんけんゲームのサービスインターフェース
 * 
 * @author takeshi.kashiwagi
 */
public interface JankenApplicationService {
    
    /**
     * プレイヤーの登録済みの手の情報取得
     * 
     * @param roomId ルームID
     * @param playerId プレイヤーID
     */
    public List<JankenChoice> getJankenChoices(Integer roomId, Integer playerId);

        
    /**
     * プレイヤーの手の登録（洗い替え）
     * 
     * @param roomId ルームID
     * @param choices プレイヤーの出し手情報
     */
    public void registerJankenChoices(Integer roomId, List<JankenChoice> choices);
}
