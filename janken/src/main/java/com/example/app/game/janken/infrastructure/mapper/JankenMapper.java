package com.example.app.game.janken.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;
import com.example.app.game.janken.infrastructure.persistence.model.JankenPlayerResultRecord;
import com.example.app.game.janken.infrastructure.persistence.model.JankenRoundResultRecord;

public interface JankenMapper {
    /**
     * 対象ルームにおける対象プレイヤーの登録済みの出し手一覧を取得
     *
     * @param roomId   ルームID
     * @param playerId プレイヤーID
     * @return 対象ルームにおける対象プレイヤーの出し手情報
     */
    List<JankenChoice> selectChoices(
                    @Param("roomId") Integer roomId,
                    @Param("playerId") Integer playerId);

    /**
     * 対象ルームにおける全プレイヤーの出し手一覧を取得
     *
     * @param roomId   ルームID
     * @return 対象ルームにおける全プレイヤーの出し手情報
     */
    List<JankenChoice> selectChoicesByRoomId(Integer roomId);

    /**
     * プレイヤーの登録済みの出し手を削除
     *
     * @param roomId   ルームID
     * @param playerId プレイヤーID
     */
    void deleteChoices(
                    @Param("roomId") Integer roomId,
                    @Param("playerId") Integer playerId);

    /**
     * プレイヤーの登録済みの出し手を登録
     * 
     * <p>洗い替えのため、UPDATEメソッドは無し。
     *
     * @param choices 出し手情報のリスト
     */
    void insertChoices(@Param("choices") List<JankenChoice> choices);
    
    /**
     * ラウンドごとのじゃんけん結果を削除。insertJankenRoundResultsとセットで使って洗い替えする。
     * 
     * @param choices 出し手情報のリスト
     */
    void deleteJankenRoundResults(Integer roomId);
    
    /**
     * ラウンドごとのじゃんけん結果を登録
     * 
     * <p>洗い替えのため、UPDATEメソッドは無し。
     *
     * @param choices 出し手情報のリスト
     */
    void insertJankenRoundResults(List<JankenRoundResultRecord> roundResults);
    
    /**
     * プレイヤーごとのじゃんけん結果を削除。insertJankenPlayerResultsとセットで使って洗い替えする。
     * 
     * @param choices 出し手情報のリスト
     */
    void deleteJankenPlayerResults(Integer roomId);
    
    /**
     * プレイヤーごとのじゃんけん結果を登録
     * 
     * <p>洗い替えのため、UPDATEメソッドは無し。
     *
     * @param playerResults プレイヤーごとのじゃんけん結果のリスト
     */
    void insertJankenPlayerResults(List<JankenPlayerResultRecord> playerResults);

}
