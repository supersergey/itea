package com.example.demo.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Post {
    String title;
    String body;
}
