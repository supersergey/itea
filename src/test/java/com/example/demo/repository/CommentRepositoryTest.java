package com.example.demo.repository;

import com.example.demo.repository.model.CommentEntity;
import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldReturnCommentById() {
        var actual = commentRepository.findById(1).get();
        assertEquals("Перший комент", actual.getTitle());
        assertEquals("Тіло першого комента", actual.getBody());
        assertEquals(3, actual.getPostEntity().getId());
        assertEquals(1, actual.getUser().getId());
    }

    @Test
    void shouldReturnNullForInvalidCommentId() {
        var actual = commentRepository.findById(-1).orElse(null);
        assertNull(actual);
    }

    @Test
    void shouldSaveANewComment() {
        var user = userRepository.findById(1);
        var post = postRepository.findById(2).get();

        var actual = commentRepository.save(new CommentEntity(
                null,
                "My new comment title",
                "My new comment body",
                post,
                user
        ));
        assertNotNull(actual);
        assertTrue(actual.getId() > 0);
    }

    @Test
    void shouldReturnNumberOfComments() {
        var actual = commentRepository.count();
        assertEquals(4, actual);
    }

    @Test
    void shouldReturnAllComments() {
        var actual = commentRepository.findAll();
        assertNotNull(actual);

        List<CommentEntity> actualList = new ArrayList<>();
        actual.forEach(actualList::add);
        assertThat(actual).isNotEmpty();
        assertEquals(4, actualList.size());
    }

    @Test
    void shouldReturnCommentsByPostId() {
        var actual = commentRepository.findByPostEntityId(2);
        assertNotNull(actual);
        assertThat(actual).isNotEmpty();
        assertEquals(1, actual.size());
    }

    @Test
    void shouldReturnCommentsByUserId() {
        var actual = commentRepository.findByUserId(1);
        assertNotNull(actual);
        assertThat(actual).isNotEmpty();
        assertEquals(2, actual.size());
    }

}