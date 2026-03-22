package com.example.app.game.janken.domain.model.round;

/**
 * じゃんけんのターン番号を粟原素VOクラス
 *  
 *  @author takeshi.kashiwagi
 */
public record OrderNo(int value) {
    public OrderNo {
        if (value <= 0) {
            throw new IllegalArgumentException("OrderNo must be positive");
        }
    }
}

