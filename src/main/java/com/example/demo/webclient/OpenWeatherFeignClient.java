package com.example.demo.webclient;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface OpenWeatherFeignClient {

    @RequestLine("GET " +
            "?" +
            "lon" + "=" + "{lat}" + "&" +
            "lat" + "=" + "{lon}" + "&" +
            "units" + "=" + "{units}" + "&" +
            "apiKey" + "=" + "{apiKey}"
    )
    Forecast getForecast(@Param(value = "lat") String latitude,
                         @Param(value = "lon") String longitude,
                         @Param String units,
                         @Param String apiKey);

    @RequestLine("GET " +
            "?" +
            "q" + "=" + "{q}" + "&" +
            "limit" + "=" + "{limit}" + "&" +
            "apiKey" + "=" + "{apiKey}"
    )
    List<Location> getLocation(@Param(value = "q") String locationName,
                               @Param String limit,
                               @Param String apiKey);
}
