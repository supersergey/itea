package com.example.demo.autoconfig;

import com.itea.demo.LocalTimeProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocalTimeProviderAutoConfigurationTest {

    @Autowired
    private LocalTimeProvider localTimeProvider;

    @Test
    void getLocalTimeProvider() {
        assertThat(localTimeProvider).isNotNull();
    }
}