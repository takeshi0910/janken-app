package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.app.domain.janken.vo.OrderNo;

@Converter(autoApply = true)
public class OrderNoConverter implements AttributeConverter<OrderNo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderNo attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public OrderNo convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new OrderNo(dbData);
    }
}
