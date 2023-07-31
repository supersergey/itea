package com.example.demo.controller.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Post post;
    private String body;
}