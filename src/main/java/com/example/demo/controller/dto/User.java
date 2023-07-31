package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record User(
        @NotNull @Size(min = 2, max = 40) String name,
        @NotNull @Size(min = 2, max = 40) String lastName) { }
