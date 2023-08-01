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
        assertThat(actual.getFirstName()).isEqualTo("George");
        assertThat(actual.getLastName()).isEqualTo("Bush");
    }

    @Test
    void shouldReturnNullForInvalidUserId() {
        var actual = userRepository.findById(-1);
        assertThat(actual).isNull();
    }

    @Test
    void shouldSaveANewUser() {
        var actual = userRepository.save(new User(
                null,
                "Bobie",
                "Dylan",
                Collections.emptyList(),
                Collections.emptyList()
        ));
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
    void shouldReturnUsersLastNamesWithTheBiggestNumberOfPosts() {
        var actual = userRepository.findUsersLastNamesWithTheBiggestNumberOfPosts();
        assertThat(actual).isNotEmpty();
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual).isEqualTo(List.of("Smith"));
    }

    @Test
    void shouldAddPostsToUser() {
        var user = new User(
                null, "Taras", "Petrenko", UserRole.ADMIN, Collections.emptyList(), Collections.emptyList()
        );
        var saved = userRepository.save(user);
        postRepository.saveAll(
                List.of(
                        new PostEntity(null, "123", "456", user, Collections.emptyList()),
                        new PostEntity(null, "123", "456", user, Collections.emptyList())
                ));

//        user.setFirstName("Petro");
        // якщо розкоментувати цю команду, чи буде тест все ще працювати? чому?
        // що зміниться, якщо прибрати анотацію @Transaction з тестового класа?

        var actual = userRepository.findById(saved.getId()).get();

        assertThat(actual.getPosts()).hasSize(0);
        assertThat(actual.getFirstName()).isEqualTo("Taras");
    }

    @Test
    void shouldFindUserByPostTitle() {
        var userWithPostTitle = userRepository.save(
                new User(
                        null, "Taras", "Petrenko", UserRole.ADMIN, Arrays.asList(), Collections.emptyList())
        );
        userWithPostTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Post Title", "PostBody", userWithPostTitle, Collections.emptyList()))
        );

        var userWithWithoutTitle = userRepository.save(
                new User(
                        null, "Petro", "Petrenko", UserRole.ADMIN, Arrays.asList(), Collections.emptyList())
        );
        userWithWithoutTitle.setPosts(
                Arrays.asList(new PostEntity(null, "Another title", "PostBody", userWithWithoutTitle, Collections.emptyList()))
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