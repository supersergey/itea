package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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