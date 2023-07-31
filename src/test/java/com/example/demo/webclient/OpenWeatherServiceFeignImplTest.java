package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenWeatherServiceFeignImplTest {
    @Autowired
    private  OpenWeatherServiceFeignImpl client;

    @Test
    void shouldGetForecast() {
        var actual = client.getForecast("50", "30", "metric");
        System.out.println(actual);
        assertThat(actual).isNotNull();
    }
}