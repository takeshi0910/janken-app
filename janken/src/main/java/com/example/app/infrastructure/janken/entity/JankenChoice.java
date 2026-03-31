package com.example.app.infrastructure.janken.entity;

import java.time.LocalDateTime;

import com.example.app.domain.janken.model.JankenHand;
import com.example.app.domain.janken.model.OrderNo;
import com.example.app.domain.room.PlayerId;
import com.example.app.domain.room.RoomId;

import lombok.Getter;
import lombok.Setter;


/**
 * janken_choiceテーブルのデータ登録用POJO。
 * 
 * * Entityに切り替え予定
 * 
 * <p>本人しか編集できないため、updated_idは不要。また、洗い替えするためupdated_atも不要。
 * 
 * <p> created_at は JPA の監査ではなく、アプリ側で明示的に設定する。
 * （MyBatis で登録するため、JPA の @CreatedDate は使用しない）
 */
@Getter
@Setter
public class JankenChoice {

    private RoomId roomId;
    private OrderNo orderNo;
    private PlayerId playerId;
    private JankenHand jankenHand;
    private LocalDateTime createdAt;
    
    public JankenChoice() {
    }

    public JankenChoice(RoomId roomId, OrderNo orderNo, PlayerId playerId, JankenHand jankenHand) {
        this.roomId = roomId;
        this.orderNo = orderNo;
        this.playerId = playerId;
        this.jankenHand = jankenHand;
        this.createdAt = LocalDateTime.now();
    }
}