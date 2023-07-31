package com.example.demo.webclient;

import feign.Param;
import feign.RequestLine;

public interface OpenWeatherFeignClient {
    @RequestLine("GET /forecast?apiKey={apiKey}&lat={lat}&lon={lon}&units={units}")
    Forecast getForecast(@Param(value = "lat") String latitude,
                         @Param(value = "lon") String longitude,
                         @Param(value = "units")  String units,
                         @Param(value = "apiKey") String apiKey);
}
