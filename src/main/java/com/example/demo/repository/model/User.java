package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class User {
    @Id
    Integer id;
    String firstName;
    String lastName;
}