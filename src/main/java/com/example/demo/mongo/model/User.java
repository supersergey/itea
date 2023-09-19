package com.example.demo.mongo.model;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
public class User {

    @Id
    String id;

    String firstName;

    String lastName;
    UserRole role;

    List<Post> posts = new ArrayList<>();
}
