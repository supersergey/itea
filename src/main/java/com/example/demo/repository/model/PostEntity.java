package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("post")
public class PostEntity {

    @Id
    private Integer id;

    private String title;
    private String body;

    private Integer userId;
}
