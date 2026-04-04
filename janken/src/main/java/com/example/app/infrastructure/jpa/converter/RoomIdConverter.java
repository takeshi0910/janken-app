package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.app.domain.room.vo.RoomId;

@Converter(autoApply = true)
public class RoomIdConverter implements AttributeConverter<RoomId, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RoomId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public RoomId convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new RoomId(dbData);
    }
}
