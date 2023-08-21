package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    @Qualifier("springDataUserRepository")
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldReturnUserById() {
        var actual = userRepository.findById(2);
        assertThat(actual).isPresent();
        var user = actual.get();
        assertThat(user.getFirstName()).isEqualTo("George");
        assertThat(user.getLastName()).isEqualTo("Bush");
    }

    @Test
    void shouldReturnNullForInvalidUserId() {
        var actual = userRepository.findById(-1);
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldSaveANewUser() {
        var actual = userRepository.save(new User(null, "Bobie", "Dylan", UserRole.USER, Collections.emptyList()));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);
    }

    @Test
    void shouldFindAUserByFirstAndLastName() {
        var actual = userRepository.existsByFirstNameAndLastName("Joe", "Biden");
        assertThat(actual).isTrue();
    }

    @Test
    void shouldNotFindAUserByWrongFirstAndLastName() {
        var actual = userRepository.existsByFirstNameAndLastName("ababa", "ajahj");
        assertThat(actual).isFalse();
    }

    @Test
    void shouldFindUserLastNameWithMaxPosts()
    {
        var actual = repository.getUserLastNameWithMaxPosts();
        assertThat(actual).isNotNull();

    void shouldAddPostsToUser() {
        var user = new User(
                null, "Taras", "Petrenko", UserRole.ADMIN, Collections.emptyList()
        );
        var saved = userRepository.save(user);
        postRepository.saveAll(
                List.of(
                        new PostEntity(null, "123", "456", saved),
                        new PostEntity(null, "123", "456", saved)
                ));

        var actual = userRepository.findById(saved.getId()).get();

        assertThat(saved.getPosts()).hasSize(2);
        assertThat(actual.getFirstName()).isEqualTo("Taras");
    }

    @Test
    void shouldFindUserByPostTitle() {
        var userWithPostTitle = userRepository.save(
                new User(
                        null, "Taras", "Petrenko", UserRole.ADMIN, Arrays.asList())
        );
        userWithPostTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Post Title", "PostBody", userWithPostTitle))
        );

        var userWithWithoutTitle = userRepository.save(
                new User(
                        null, "Petro", "Petrenko", UserRole.ADMIN, Arrays.asList())
        );
        userWithWithoutTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Another title", "PostBody", userWithWithoutTitle))
        );

        var user = userRepository.findByPostsTitle("Post Title");

        assertThat(user)
                .extracting(User::getFirstName)
                .containsExactly("Taras");
    }

    @Test
    void shouldReturnAUserWithMaxPosts() {
        var actual = userRepository.findUserWithMaximalNumberOfPosts();

        assertThat(actual).extracting(User::getLastName).isEqualTo("Smith");
    }

    @Test
    void shouldReturnAllUsersPagedAndSorted() {
        var actual = userRepository.findAll(
                PageRequest.of(0, 2, Sort.by("lastName").descending())
        );

        assertThat(actual).extracting(User::getLastName)
                .containsExactly("Smith", "Charles");
    }
}