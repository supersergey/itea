package com.example.demo.repository;

import com.example.demo.repository.model.PostEntity;
import com.example.demo.repository.model.User;
import com.example.demo.repository.model.UserRole;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DBRider
@DBUnit(caseSensitiveTableNames = true, schema = "public")
class UserRepositoryTest {

    @Autowired
    @Qualifier("springDataUserRepository")
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserDataFactory userDataFactory;

    @Container
    static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:14.2")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
        registry.add("schema", () -> "public");
    }

    @BeforeAll
    static void setUp() {
        postgresqlContainer.start();
    }

    @AfterAll
    static void tearDown() {
        postgresqlContainer.stop();
    }

    @Test
    @DataSet(value = "users.yml", cleanAfter = true)
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
    @ExpectedDataSet(value = "savedUser.yml")
    void shouldSaveANewUser() {
        userRepository.saveAndFlush(new User(null, "Bobie", "Dylan", UserRole.USER, Collections.emptyList()));
    }

    @Test
    @DataSet("users.yml")
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
    @Disabled
    void shouldAddPostsToUser() {
        var saved = userDataFactory.addNewUser( null, "Taras", "Petrenko", UserRole.ADMIN);
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
    @Transactional
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
    @DataSet(value = {"users.yml", "posts.yml"}, cleanAfter = true)
    void shouldReturnAUserWithMaxPosts() {
        var actual = userRepository.findUserWithMaximalNumberOfPosts();

        assertThat(actual).extracting(User::getLastName).isEqualTo("Smith");
    }

    @Test
    @DataSet(value = "users.yml", cleanAfter = true)
    void shouldReturnAllUsersPagedAndSorted() {
        var actual = userRepository.findAll(
                PageRequest.of(0, 2, Sort.by("lastName").descending())
        );

        assertThat(actual).extracting(User::getLastName)
                .containsExactly("Smith", "Charles");
    }
}