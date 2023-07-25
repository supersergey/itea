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

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void shouldReturnPostById() {
        var actual = postRepository.findById(2).get();
        assertEquals("Мій другий пост", actual.getTitle());
        assertEquals("Всім ще більше вітань!", actual.getBody());
        assertEquals(4, actual.getUser().getId());
    }

    @Test
    void shouldReturnNullForInvalidPostId() {
        var actual = postRepository.findById(-1).orElse(null);
        assertNull(actual);
    }

    @Test
    void shouldSaveANewPost() {
        var user = userRepository.findById(1);

        var actual = postRepository.save(new PostEntity(
                null,
                "My new post title",
                "My new post body",
                user,
                Collections.emptyList()
        ));
        assertNotNull(actual);
        assertTrue(actual.getId() > 0);
    }

    @Test
    void shouldReturnNumberOfPosts() {
        var actual = postRepository.count();
        assertEquals(4, actual);
    }

    @Test
    void shouldReturnAllPosts() {
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

    @Test
    void shouldAddCommentsToPost() {
        var user = userRepository.findById(1);

        var post = new PostEntity(
                null,
                "New post title",
                "New post body",
                user,
                Collections.emptyList()
        );

        var savedPost = postRepository.save(post);

        var savedComments = commentRepository.saveAll(List.of(
                new CommentEntity(null, "Comment 1 title", "Comment 1 body", post, user),
                new CommentEntity(null, "Comment 2 title", "Comment 2 body", post, user)
        ));
        List<CommentEntity> commentEntities = new ArrayList<>();
        savedComments.forEach(commentEntities::add);
        savedPost.setComments(commentEntities);

        var actual = postRepository.findById(savedPost.getId()).get();

        assertEquals(user.getId(), actual.getUser().getId());

        assertEquals(2, actual.getComments().size());
    }

}