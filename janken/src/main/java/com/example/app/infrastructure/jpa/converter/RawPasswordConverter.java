package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.app.domain.user.vo.RawPassword;

@Converter(autoApply = true)
public class RawPasswordConverter implements AttributeConverter<RawPassword, String> {

    @Override
    public String convertToDatabaseColumn(RawPassword attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public RawPassword convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new RawPassword(dbData);
    }
}
