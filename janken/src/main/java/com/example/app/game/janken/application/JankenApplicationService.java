package com.example.app.game.janken.application;

import java.util.List;

import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;
import com.example.app.room.domain.RoomId;

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
    public List<JankenChoice> getJankenChoices(RoomId roomId, Integer playerId);

        
    /**
     * プレイヤーの手の登録（洗い替え）
     * 
     * @param roomId ルームID
     * @param choices プレイヤーの出し手情報
     */
    public void registerJankenChoices(RoomId roomId, List<JankenChoice> choices);


    /**
     * じゃんけんの対戦実施。結果をDB登録する。
     * 
     * @param roomId ルームID
     */
    public void battle(RoomId roomId);
}
