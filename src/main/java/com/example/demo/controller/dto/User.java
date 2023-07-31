package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String lastName;
    private int age;
    private List<Post> posts;
}
