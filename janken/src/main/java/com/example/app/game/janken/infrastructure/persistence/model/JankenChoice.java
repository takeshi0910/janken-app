package com.example.app.game.janken.infrastructure.persistence.model;

import java.time.LocalDateTime;

import com.example.app.game.janken.domain.model.JankenHand;

import lombok.Getter;
import lombok.Setter;


/**
 * janken_choiceテーブルのデータ登録用POJO。
 * 
 * <p>本人しか編集できないため、updated_idは不要。また、洗い替えするためupdated_atも不要。
 * 
 * <p> created_at は JPA の監査ではなく、アプリ側で明示的に設定する。
 * （MyBatis で登録するため、JPA の @CreatedDate は使用しない）
 */
@Getter
@Setter
public class JankenChoice {

    private Integer roomId;
    private Integer orderNo;
    private Integer playerId;
    private JankenHand jankenHand;
    private LocalDateTime createdAt;
    
    public JankenChoice() {
    }

    public JankenChoice(Integer roomId, Integer orderNo, Integer playerId, JankenHand jankenHand) {
        this.roomId = roomId;
        this.orderNo = orderNo;
        this.playerId = playerId;
        this.jankenHand = jankenHand;
        this.createdAt = LocalDateTime.now();
    }
}