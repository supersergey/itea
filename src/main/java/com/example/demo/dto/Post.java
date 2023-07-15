package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class Post {

    String title;
    String body;

}
