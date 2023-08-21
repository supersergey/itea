package com.example.demo.service;

import com.example.demo.repository.model.CommentEntity;
import com.example.demo.controller.dto.Comment;
import com.example.demo.repository.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class CommentConverterTest {
    private final CommentConverter commentConverter = new CommentConverter();

    @Test
    void shouldConvertToDto() {
        var commentEntity = new CommentEntity(
                null,
                "Some body",
                null,
                new User(1, "Andriy", "Shevchenko", null, null));
        var actual = commentConverter.toDto(commentEntity);

        assertThat(actual.getBody()).isEqualTo(commentEntity.getBody());
        assertThat(actual.getAuthor()).isEqualTo("Shevchenko");
    }

    @Test
    void shouldConvertToEntity() {
        var commentDto = new Comment("Andriy Shevchenko", "Comment body", new Date(), 1);
        var actual = commentConverter.toEntity(commentDto);

        assertThat(actual.getBody()).isEqualTo(commentDto.getBody());
    }
}