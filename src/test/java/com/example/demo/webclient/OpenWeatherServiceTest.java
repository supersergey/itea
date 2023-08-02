package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenWeatherServiceTest {

    @Autowired
    //@Qualifier("weatherServiceWithFeign")
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
    void getCoordinates(){
        var actual = openWeatherService.getCoordinates("Kyiv", 1);
        System.out.println(actual);
        assertThat(actual.getCountry()).isEqualTo("UA");
        assertThat(actual.getLatitude()).isEqualTo("50.4500336");
        assertThat(actual.getLongitude()).isEqualTo("30.5241361");
        assertThat(actual).isNotNull();
    }

}