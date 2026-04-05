package com.example.app.infrastructure.jankenchoice.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.infrastructure.jankenchoice.entity.JankenChoiceEntity;

/**
 * janken_choiceテーブル repository
 * 
 * @author takeshi.kashiwagi
 */
public interface JpaJankenChoiceRepository extends JpaRepository<JankenChoiceEntity, Integer> {

    /**
     * 対象ルームにおける全プレイヤーの出し手一覧を取得
     *
     * @param roomId   ルームID
     * @return 対象ルームにおける全プレイヤーの出し手情報
     */
    List<JankenChoiceEntity> findByRoomId(Integer roomId);

    /**
     * 対象ルームにおける対象プレイヤーの登録済みの出し手一覧を取得
     *
     * @param roomId   ルームID
     * @param playerId プレイヤーID
     * @return 対象ルームにおける対象プレイヤーの出し手情報
     */
    List<JankenChoiceEntity> findByRoomIdAndPlayerId(Integer roomId, Integer playerId);

    /**
     * 対象ルームにおける対象プレイヤーの出し手レコードを一括削除
     *
     * @param roomId   ルームID
     */
    void deleteByRoomIdAndPlayerId(Integer roomId, Integer playerId);

    /**
     * 対象ルームの出し手レコードを一括削除
     *
     * @param roomId   ルームID
     */
    void deleteByRoomId(Integer roomId);
}
