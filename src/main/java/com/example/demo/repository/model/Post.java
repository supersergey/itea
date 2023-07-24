package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("post")
public class Post {
    @Id
    Integer id;
    String title;
    String body;
    Integer userId;
}
