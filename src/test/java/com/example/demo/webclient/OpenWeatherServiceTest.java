package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenWeatherServiceTest {

    @Autowired
    @Qualifier("weatherServiceWithFeign")
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
    void getLocation() {
        var actual = openWeatherService.getLocation("Kyiv", 1);
        String actualLatitude = actual.get(0).getLatitude();
        String actualLongitude = actual.get(0).getLongitude();

        System.out.println(actual);

        assertThat(actual).isNotNull();

        assertThat(actualLatitude).isEqualTo("50.4500336");
        assertThat(actualLongitude).isEqualTo("30.5241361");
    }
}