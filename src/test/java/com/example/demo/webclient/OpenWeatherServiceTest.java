package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OpenWeatherServiceTest {

    @Autowired
    private OpenWeatherService openWeatherService;

    @Test
    void getForecast() {
        var actual = openWeatherService.getForecast("30.45",
                "50.45",
                "metric");

        System.out.println(actual);

        assertThat(actual).isNotNull();
    }

    @Test
    void shouldLoadCorrectComponent() {
        assertThat(openWeatherService).isExactlyInstanceOf(OpenWeatherServiceFeignImpl.class);
    }
}