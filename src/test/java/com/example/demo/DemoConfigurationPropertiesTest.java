package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.unit.DataSize;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "demo.apiKey=custom")
class DemoConfigurationPropertiesTest {
    @Autowired
    private DemoConfigurationProperties properties;

    @Test
    void shouldResolveConfigurationProperties() {
        assertThat(properties).isNotNull();
        assertThat(properties.getFeign()).isTrue();
        assertThat(properties.getRest()).isFalse();
        assertThat(properties.getSize()).isEqualTo(DataSize.ofGigabytes(1));
        assertThat(properties.getFrequency()).hasDays(1);
        System.out.println(properties.getApiKey().length());
    }
}