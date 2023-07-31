package com.example.demo.webclient.openfeignclient;

import com.example.demo.webclient.Forecast;
import com.example.demo.webclient.Location;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface OpenWeatherApi {

@GetMapping
    Forecast getForecast(
            @RequestParam("lon") String longitude,
            @RequestParam("lat") String latitude,
            @RequestParam String units,
            @RequestParam String apiKey);

    @RequestLine("GET " +
            "?" +
            "q" + "=" + "{locationName}" + "&" +
            "limit" + "=" + "{limit}" + "&" +
            "apiKey" + "=" + "{apiKey}"
    )
    List<Location> getLocation(@Param String locationName,
                               @Param String limit,
                               @Param String apiKey);
}
