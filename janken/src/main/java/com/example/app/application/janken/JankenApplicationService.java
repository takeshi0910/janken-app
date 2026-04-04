package com.example.app.application.janken;

import java.util.List;

import com.example.app.domain.room.vo.RoomId;
import com.example.app.domain.user.vo.UserId;
import com.example.app.infrastructure.janken.entity.JankenChoice;

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
    public List<JankenChoice> getJankenChoices(RoomId roomId, UserId userId);

        
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
