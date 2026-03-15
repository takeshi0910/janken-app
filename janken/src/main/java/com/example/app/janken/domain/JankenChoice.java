package com.example.app.janken.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.app.janken.JankenHand;

/**
 * janken_choiceテーブルに対応したエンティティ
 * 
 * <p>本人しか編集できないため、updated_idは不要。また、洗い替えするためupdated_atも不要。
 */
@Entity
@Table(name = "janken_choice")
@EntityListeners(AuditingEntityListener.class)
@IdClass(JankenChoiceId.class)
public class JankenChoice {

    @Id
    private Integer roomId;

    @Id
    private Integer order;

    @Id
    @CreatedBy
    private Integer createdId;

    @Enumerated(EnumType.STRING)
    private JankenHand jankenHand;

    @CreatedDate // 登録日時の自動反映 INSERTの時だけ動く
    private LocalDateTime createdAt;

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setJankenHand(JankenHand jankenHand) {
        this.jankenHand = jankenHand;
    }

}
