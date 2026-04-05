package com.example.app.application.janken;

import java.util.List;

import com.example.app.domain.janken.model.JankenChoiceRecord;
import com.example.app.domain.room.vo.RoomId;
import com.example.app.domain.user.vo.UserId;
import com.example.app.presentation.janken.JankenChoiceForm;

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
    public List<JankenChoiceRecord> getJankenChoices(RoomId roomId, UserId userId);

        
    /**
     * プレイヤーの手の登録（洗い替え）
     * 
     * @param roomId ルームID
     * @param form プレイヤーの出し手情報(画面入力値）
     */
    public void registerJankenChoices(RoomId roomId, JankenChoiceForm form);


    /**
     * じゃんけんの対戦実施。結果をDB登録する。
     * 
     * @param roomId ルームID
     */
    public void battle(RoomId roomId);
}
