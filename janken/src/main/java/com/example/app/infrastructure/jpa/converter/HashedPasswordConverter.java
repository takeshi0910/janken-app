package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.app.domain.user.vo.HashedPassword;

@Converter(autoApply = true)
public class HashedPasswordConverter implements AttributeConverter<HashedPassword, String> {

    @Override
    public String convertToDatabaseColumn(HashedPassword attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public HashedPassword convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new HashedPassword(dbData);
    }
}
