package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

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

        var actual = postRepository.save(new PostEntity(null, "My new post title", "My new post body", user));
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

    @Transactional
    void findUserByPostTitle() {
        var userWithPostTitle = userRepository.save(
                new User(
                        null, "Taras", "Petrenko", Arrays.asList())
        );
        userWithPostTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Post Title", "PostBody", userWithPostTitle))
        );

        var userWithWithoutTitle = userRepository.save(
                new User(
                        null, "Petro", "Petrenko", Arrays.asList())
        );
        userWithWithoutTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Another title", "PostBody", userWithWithoutTitle))
        );

        var actual = postRepository.findUserByPostTitle("Post Title");

        assertThat(actual).extracting(User::getLastName)
                .containsExactly("Petrenko");
    }
}