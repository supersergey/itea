package com.example.demo.webclient.feign;

import com.example.demo.webclient.Coordinates;
import com.example.demo.webclient.Forecast;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OpenWeatherApi {
    @RequestLine("GET " +
            "?" +
            "lon" + "=" + "{longitude}" + "&" +
            "lat" + "=" + "{latitude}" + "&" +
            "units" + "=" + "{units}" + "&" +
            "apiKey" + "=" + "{apiKey}"
    )
    Forecast getForecast(@Param String latitude,
                         @Param String longitude,
                         @Param String units,
                         @Param String apiKey);

    @RequestLine("GET " +
            "?" +
            "q" + "=" + "{name}" + "&" +
            "limit" + "=" + "{limit}" + "&" +
            "apiKey" + "=" + "{apiKey}"
    )
    List<Coordinates> getCoordinates(@Param String name,
                                     @Param String limit,
                                     @Param String apiKey);
}
