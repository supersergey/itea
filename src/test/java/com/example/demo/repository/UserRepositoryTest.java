package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        var actual = userRepository.save(new User(null, "Bobie", "Dylan", Collections.emptyList()));
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
    void shouldAddPostsToUser() {
        var user = new User(
                null, "Taras", "Petrenko", Collections.emptyList()
        );
        var saved = userRepository.save(user);
        postRepository.saveAll(
                List.of(
                        new PostEntity(null, "123", "456", user),
                        new PostEntity(null, "123", "456", user)
                ));

//        user.setFirstName("Petro");
        // якщо розкоментувати цю команду, чи буде тест все ще працювати? чому?
        // що зміниться, якщо прибрати анотацію @Transaction з тестового класа?

        var actual = userRepository.findById(saved.getId());

        assertThat(actual.getPosts()).hasSize(2);
        assertThat(actual.getFirstName()).isEqualTo("Taras");
    }
}