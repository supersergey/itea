package com.example.demo.webclient.openfeignclient;

import com.example.demo.webclient.Forecast;
import com.example.demo.webclient.Location;
import com.example.demo.webclient.OpenWeatherService;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("weatherServiceWithFeign")
public class OpenWeatherServiceFeignImpl implements OpenWeatherService {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String BASE_LOCATION_URL = "https://api.openweathermap.org/geo/1.0/direct";

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    public Forecast getForecast(String longitude, String latitude, String units) {

        OpenWeatherApi openWeatherApi = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherApi.class, BASE_URL);

        return openWeatherApi.getForecast(longitude, latitude, units, apiKey);
    }

    public List<Location> getLocation(String locationName, int limit) {

        OpenWeatherApi openWeatherApi = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherApi.class, BASE_LOCATION_URL);

            return openWeatherApi.getLocation(locationName, String.valueOf(limit), apiKey);
    }
}
