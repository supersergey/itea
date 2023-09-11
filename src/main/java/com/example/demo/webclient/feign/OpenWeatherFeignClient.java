package com.example.demo.webclient.feign;

import com.example.demo.webclient.Coordinates;
import com.example.demo.webclient.Forecast;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface OpenWeatherFeignClient {
    @RequestLine("GET ?apiKey={apiKey}&lat={lat}&lon={lon}&units={units}")
    Forecast getForecast(@Param(value = "lat") String latitude,
                         @Param(value = "lon") String longitude,
                         @Param(value = "units")  String units,
                         @Param(value = "apiKey") String apiKey);

    @RequestLine("GET ?q={name}&limit={limit}&apiKey={apiKey}")
    List<Coordinates> getCoordinates(@Param(value = "name") String name,
                                     @Param(value = "limit") String limit,
                                     @Param(value = "apiKey") String apiKey);
}
