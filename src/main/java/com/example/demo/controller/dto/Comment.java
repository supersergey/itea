package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Comment {

    @NotBlank(message = "Comment title should not be blank")
    private final String title;

    @NotBlank(message = "Comment body should not be blank")
    private final String body;
}
