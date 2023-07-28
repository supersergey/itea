package com.example.demo.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    void getForecast() {
        System.out.println(weatherService.getForecast(
                "50.45", "30.45", "metric", "361c6733f1edd21aff8e265331272630"
        ));
    }
}