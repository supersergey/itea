package com.example.demo.webclient;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenWeatherServiceTest {

    @Autowired
    private OpenWeatherService openWeatherService;

    @Test
    @Disabled
    void getForecast() {
        var actual = openWeatherService.getForecast("30.45",
                "50.45",
                "metric");

        System.out.println(actual);

        assertThat(actual).isNotNull();
    }
}
