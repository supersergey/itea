package com.example.demo.webclient;

import feign.Param;
import feign.RequestLine;

public interface OpenWeatherFeignClient {
    @RequestLine("GET")
    Forecast getForecast(@Param(value = "lat") String latitude,
                         @Param(value = "lon") String longitude,
                         @Param String units,
                         @Param String apiKey);
}
