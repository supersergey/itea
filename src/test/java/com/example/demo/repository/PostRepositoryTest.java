package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldReturnPostById() {
        var actual = postRepository.findById(2).get();
        assertEquals("Мій другий пост", actual.getTitle());
        assertEquals("Всім ще більше вітань!", actual.getBody());
        assertEquals(4, actual.getUserId());
    }

    @Test
    void shouldReturnNullForInvalidPostId() {
        var actual = postRepository.findById(-1).orElse(null);
        assertNull(actual);
    }

    @Test
    void shouldSaveANewPost() {
        var actual = postRepository.save(new PostEntity(null, "My new post title", "My new post body", 1));
        assertNotNull(actual);
        assertTrue(actual.getId() > 0);
    }

    @Test
    void shouldReturnNumberOfPosts() {
        var actual = postRepository.count();
        assertEquals(4, actual);
    }

    @Test
    void shouldReturnAllPost() {
        var actual = postRepository.findAll();
        assertNotNull(actual);

        List<PostEntity> actualList = new ArrayList<>();
        actual.forEach(actualList::add);
        assertThat(actual).isNotEmpty();
        assertEquals(4, actualList.size());
    }

    @Test
    void shouldReturnPostsByUserId() {
        var actual = postRepository.findByUserId(2);
        assertNotNull(actual);
        assertThat(actual).isNotEmpty();
        assertEquals(1, actual.size());
    }

    @Test
    void shouldReturnNumberOfPostsByUserId() {
        var actual = postRepository.countByUserId(3);
        assertEquals(2, actual);
    }

    @Test
    void shouldReturnUsersIdsWithTheBiggestNumberOfPosts() {
        var actual = postRepository.findUsersIdsWithTheBiggestNumberOfPosts();
        assertEquals(List.of(3), actual);
    }

}