package com.example.app.domain.janken.model;

/**
 * じゃんけんの出し手の登録順序 VOクラス
 *  
 *  @author takeshi.kashiwagi
 */
public record OrderNo(int value) {
    public OrderNo(String value) {
        this(Integer.valueOf(value));
    }
}

