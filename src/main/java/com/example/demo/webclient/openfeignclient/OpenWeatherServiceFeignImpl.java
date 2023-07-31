package com.example.demo.webclient.openfeignclient;

import com.example.demo.webclient.Forecast;
import com.example.demo.webclient.Location;
import com.example.demo.webclient.OpenWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("weatherServiceWithFeign")
public class OpenWeatherServiceFeignImpl implements OpenWeatherService {

    @Value("${openweathermap.apiKey}")
    private String apiKey;
    private final OpenWeatherApi weatherFeignClient;
    private final OpenWeatherApi locationFeignClient;

    public OpenWeatherServiceFeignImpl(
            @Qualifier("weatherClient") OpenWeatherApi weatherFeignClient,
            @Qualifier("locationClient") OpenWeatherApi locationFeignClient
    ) {
        this.weatherFeignClient = weatherFeignClient;
        this.locationFeignClient = locationFeignClient;
    }

    public Forecast getForecast(String longitude, String latitude, String units) {
        return weatherFeignClient.getForecast(longitude, latitude, units, apiKey);
    }

    public List<Location> getLocation(String locationName, int limit) {
        return locationFeignClient.getLocation(locationName, String.valueOf(limit), apiKey);
    }
}
