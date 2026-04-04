package com.example.app.domain.janken.vo;

import jakarta.persistence.Embeddable;

import com.example.app.domain.common.IntegerValueObject;

/**
 * じゃんけんの出し手の登録順序 VOクラス
 *  
 *  @author takeshi.kashiwagi
 */
@Embeddable
public record OrderNo(Integer value) implements IntegerValueObject {
    public OrderNo(String value) {
        this(Integer.valueOf(value));
    }
}

