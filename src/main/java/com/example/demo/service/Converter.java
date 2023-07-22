package com.example.demo.service;

public interface Converter<DTO, MODEL> {
    DTO toDto(MODEL e);
    MODEL toEntity(DTO dto);
}
