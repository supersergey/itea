package com.example.demo.controller;

import com.example.demo.controller.dto.User;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        when(userService.save(any())).thenReturn(1);
    }

    @Test
    void createUser(){
        ResponseEntity<Integer> actual = testRestTemplate.postForEntity(
                "/api/users",
                new User(RandomString.make(), RandomString.make(), "ADMIN"),
                Integer.class
        );
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(actual);
        assertThat(actual.getBody()).isEqualTo(1);
    }

    @Test
    void shouldReturnBadRequestOnInvalidUser(){
        ResponseEntity<Object> actual = testRestTemplate.postForEntity(
                "/api/users",
                new User(RandomString.make(), RandomString.make(), "123"),
                Object.class
        );
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}