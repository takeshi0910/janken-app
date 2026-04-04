package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.example.app.domain.user.vo.UserId;

@Converter(autoApply = true)
public class UserIdConverter implements AttributeConverter<UserId, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public UserId convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new UserId(dbData);
    }
}
