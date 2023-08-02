package com.example.demo.repository;

import com.example.demo.repository.model.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CustomUserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return switch (attribute) {
            case ADMIN -> "ADMIN";
            case GUEST -> "GAST";
            case USER -> "BENUTZER";
            case OWNER -> "BESITZER";
        };
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "ADMIN" -> UserRole.ADMIN;
            case "GAST" -> UserRole.GUEST;
            case "BENUTZER" -> UserRole.USER;
            case "BESITZER" -> UserRole.OWNER;
            default -> throw new IllegalArgumentException("Unknown user roles" + dbData);
        };
    }
}
