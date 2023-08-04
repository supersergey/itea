package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record User(
        @NotBlank(message = "User name should not be blank") String name,
        @NotNull @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 chars")
        String lastName,
        @NotNull
        @Pattern(regexp = "(GUEST)|(USER)|(ADMIN)|(OWNER)", message = "Unknown user role")
        @JsonIgnoreProperties
        String role
        ) { }
