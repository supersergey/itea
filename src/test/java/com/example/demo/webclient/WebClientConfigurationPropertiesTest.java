package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.unit.DataSize;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebClientConfigurationPropertiesTest {
    @Autowired
    private WebClientConfigurationProperties properties;

    @Test
    void shouldInjectProperties() {
        assertThat(properties.getMaxSize())
                .isEqualTo(DataSize.ofKilobytes(1));
        assertThat(properties.getUpdateFrequency())
                .isEqualTo(Duration.ofSeconds(60));
    }
}