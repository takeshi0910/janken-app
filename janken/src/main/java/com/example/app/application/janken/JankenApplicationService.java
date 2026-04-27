package com.example.app.application.janken;

import java.util.List;

import com.example.app.application.janken.dto.BattleResultDto;
import com.example.app.application.janken.dto.JankenRegisteredStatusDto;
import com.example.app.domain.janken.model.JankenChoiceRecord;
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
     * @param roomIdValue ルームID
     * @param userIdValue ログインユーザーID
     */
    public List<JankenChoiceRecord> getJankenChoices(Integer roomIdValue, Integer userIdValue);

        
    /**
     * プレイヤーの手の登録（洗い替え）
     * 
     * @param roomIdValue ルームID
     * @param form プレイヤーの出し手情報(画面入力値）
     */
    public void registerJankenChoices(Integer roomIdValue, JankenChoiceForm form);

    /**
     * 全プレイヤーの出し手の登録状況（登録済みor未登録）の情報取得
     * 
     * @param roomId ルームID
     * @param playerId プレイヤーID
     */
    public JankenRegisteredStatusDto getJankenRegisterdStatus(Integer roomId);
    
    /**
     * 登録された出し手情報からじゃんけんの対戦を実施し、結果をDB登録する。
     * 
     * @param roomIdValue ルームID
     */
    public void executeBattle(Integer roomIdValue);
    
    /**
     * じゃんけんの対戦結果を取得する。
     * 
     * @param roomIdValue ルームID
     */
    public BattleResultDto getBattleResult(Integer roomIdValue);
}
