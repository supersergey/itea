package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CommentRepositoryTest {
    @Autowired
    @Qualifier("springDataCommentRepository")

    CommentRepository repository;

    @Test
    void shouldFindByUserId() {
        var actual = repository.findByUserId(1);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0).getBody()).isEqualTo("Четвертий коментар");
    }

    @Test
    void shouldFindByPostId() {
        var actual = repository.findByPostEntityId(1);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0).getBody()).isEqualTo("Третій коментар");
    }
}
