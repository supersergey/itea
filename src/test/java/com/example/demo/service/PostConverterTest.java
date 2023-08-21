package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostConverterTest {

    private final PostConverter postConverter = new PostConverter();

    @Test
    void ShouldConvertToDto() {
        var postEntity = new PostEntity(
                null,
                "Post title",
                "Post body",
                new User(
                        1,
                        "Andriy",
                        "Shevchenko",
                        null,
                        null),
                null);
        var actual = postConverter.toDto(postEntity);

        assertThat(actual.getTitle()).isEqualTo(postEntity.getTitle());
        assertThat(actual.getBody()).isEqualTo(postEntity.getBody());
    }

    @Test
    void ShouldConvertToEntity() {
        var postDto = new Post("Post title", "Post body", 1);
        var actual = postConverter.toEntity(postDto);

        assertThat(actual.getTitle()).isEqualTo(postDto.getTitle());
        assertThat(actual.getBody()).isEqualTo(postDto.getBody());
    }
}