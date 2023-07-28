package com.example.demo.client;

import feign.Param;
import feign.RequestLine;

public interface WebClientFeign {
    @RequestLine("GET /forecast?lon={lon}&lat={lat}&units={units}&apiKey={apiKey}")
    Forecast getForecast(
            @Param("lon") String longitude,
            @Param("lat") String latitude,
            @Param("units") String units,
            @Param("apiKey") String key);
}
