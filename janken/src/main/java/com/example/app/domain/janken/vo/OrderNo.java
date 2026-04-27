package com.example.app.domain.janken.vo;

/**
 * じゃんけんの出し手の登録順序 VO
 *  
 *  @author takeshi.kashiwagi
 */
public record OrderNo(Integer value) implements Comparable<OrderNo> {

    public OrderNo {
        if (value == null || value < 1) {
            throw new IllegalArgumentException("OrderNo must be >= 1");
        }
    }

    public Integer value() {
        return value;
    }
    
    public static OrderNo from(Integer value) {
        return new OrderNo(value);
    }
    
    public static OrderNo from(String value) {
        return new OrderNo(Integer.valueOf(value));
    }

    @Override
    public int compareTo(OrderNo other) {
        return Integer.compare(this.value, other.value);
    }
}

