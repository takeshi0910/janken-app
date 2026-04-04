package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.app.domain.room.vo.PlayerId;

@Converter(autoApply = true)
public class PlayerIdConverter implements AttributeConverter<PlayerId, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PlayerId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public PlayerId convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new PlayerId(dbData);
    }
}
