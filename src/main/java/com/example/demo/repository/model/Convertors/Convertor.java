package com.example.demo.repository.model.Convertors;

public interface Convertor<DTO, MODEL> {
    DTO toDto(MODEL e);
    MODEL toEntity(DTO dto);
}