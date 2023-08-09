package com.example.demo.service;

import com.example.demo.controller.dto.Comment;
import com.example.demo.repository.model.CommentEntity;
import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentConverterTest {
    private final CommentConverter commentConverter = new CommentConverter();

    @Test
    void shouldConvertToDto() {
        var commentEntity = new CommentEntity(
                null,
                "Comment title",
                "Comment body",
                null,
                new User(1, null, null, null, null, null));
        var actual = commentConverter.toDto(commentEntity);

        assertThat(actual.getTitle()).isEqualTo(commentEntity.getTitle());
        assertThat(actual.getBody()).isEqualTo(commentEntity.getBody());
        assertThat(actual.getAuthorId()).isEqualTo(1);
    }

    @Test
    void shouldConvertToEntity() {
        var commentDto = new Comment("Comment title", "Comment body", 1);
        var actual = commentConverter.toEntity(commentDto);

        assertThat(actual.getTitle()).isEqualTo(commentDto.getTitle());
        assertThat(actual.getBody()).isEqualTo(commentDto.getBody());
    }

}