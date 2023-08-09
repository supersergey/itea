package com.example.demo.service;

import com.example.demo.controller.dto.Post;
import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostConverterTest {
    private final PostConverter postConverter = new PostConverter();

    @Test
    void shouldConvertToDto() {
        var postEntity = new PostEntity(null, "Post title", "Post body", null, null);
        var actual = postConverter.toDto(postEntity);

        assertThat(actual.getTitle()).isEqualTo(postEntity.getTitle());
        assertThat(actual.getBody()).isEqualTo(postEntity.getBody());
    }

    @Test
    void shouldConvertToEntity() {
        var postDto = new Post("Post title", "Post body");
        var actual = postConverter.toEntity(postDto);

        assertThat(actual.getTitle()).isEqualTo(postDto.getTitle());
        assertThat(actual.getBody()).isEqualTo(postDto.getBody());
    }

}