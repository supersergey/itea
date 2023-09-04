package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void findUserByPostTitle() {
        var userWithPostTitle = userRepository.save(
                new User(
                        null, "Taras", "Petrenko", UserRole.USER, new ArrayList<>())
        );
        userWithPostTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Post Title", "PostBody", userWithPostTitle))
        );

        var userWithWithoutTitle = userRepository.save(
                new User(
                        null, "Petro", "Petrenko", UserRole.USER, new ArrayList<>())
        );
        userWithWithoutTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Another title", "PostBody", userWithWithoutTitle))
        );

        var actual = postRepository.findUserByPostTitle("Post Title");

        assertThat(actual).extracting(User::getLastName)
                .containsExactly("Petrenko");
    }
}