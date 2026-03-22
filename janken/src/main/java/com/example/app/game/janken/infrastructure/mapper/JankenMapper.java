package com.example.app.game.janken.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.game.janken.infrastructure.persistence.model.JankenChoice;

public interface JankenMapper {
    /**
     * プレイヤーの登録済みの出し手一覧を取得
     *
     * @param roomId   ルームID
     * @param playerId プレイヤーID
     */
    List<JankenChoice> selectChoices(
                    @Param("roomId") Integer roomId,
                    @Param("playerId") Integer playerId);

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


}
