package com.example.app.infrastructure.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.app.domain.user.vo.UserName;

@Converter(autoApply = true)
public class UserNameConverter implements AttributeConverter<UserName, String> {

    @Override
    public String convertToDatabaseColumn(UserName attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public UserName convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new UserName(dbData);
    }
}
