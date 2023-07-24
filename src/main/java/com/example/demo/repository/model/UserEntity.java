package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    Integer id;
    String firstName;
    String lastName;
    int age;
}