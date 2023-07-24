package com.example.demo.repository.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static com.example.demo.repository.model.UserRole.*;

@Converter(autoApply = true)
public class UserRoleAttributeConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return switch (attribute) {
            case GUEST -> "Gast";
            case USER -> "Benutzer";
            case ADMIN -> "Admin";
            case OWNER -> "Besitzer";
        };
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "Gast" -> GUEST;
            case "Benutzer" -> USER;
            case "Admin" -> ADMIN;
            case "Besitzer" -> OWNER;
            default -> throw new IllegalArgumentException("Invalid user role: " + dbData);
        };
    }
}
