package com.example.demo.webclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "openWeather", url = "https://api.openweathermap.org/data/2.5/forecast")
public interface OpenWeatherFeignClient {
    @GetMapping
    Forecast getForecast(@RequestParam(value = "lat") String latitude,
                         @RequestParam(value = "lon") String longitude,
                         @RequestParam String units,
                         @RequestParam String apiKey);
}
