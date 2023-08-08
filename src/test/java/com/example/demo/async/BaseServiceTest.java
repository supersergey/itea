package com.example.demo.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {BaseService.class, AsyncService.class})
@EnableAsync
class BaseServiceTest {

    @Autowired
    private BaseService baseService;

    @Test
    void doSomething() {
        var actual = baseService.doSomething();

        assertThat(actual).isEqualTo("result");
    }
}