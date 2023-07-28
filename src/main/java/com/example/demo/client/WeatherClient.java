package com.example.demo.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "https://api.openweathermap.org/data/2.5/")
public interface WeatherClient {
    @GetExchange("/forecast")
    Forecast getForecast(@RequestParam String lat,
                               @RequestParam String lon,
                               @RequestParam String units,
                               @RequestParam String apiKey
    );
}
