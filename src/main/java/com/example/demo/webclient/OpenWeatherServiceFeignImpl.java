package com.example.demo.webclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("feign")
@ConditionalOnProperty(prefix = "demo", name = "feign", havingValue = "true")
public class OpenWeatherServiceFeignImpl implements OpenWeatherService {
    private final OpenWeatherFeignClient weatherFeignClient;
    private final OpenWeatherFeignClient locationFeignClient;
    private final String apiKey;

    public OpenWeatherServiceFeignImpl(@Qualifier("weatherClient") OpenWeatherFeignClient weatherFeignClient,
                                       @Qualifier("locationClient") OpenWeatherFeignClient locationFeignClient,
                                       @Value("${openweathermap.apiKey}") String apiKey) {
        this.weatherFeignClient = weatherFeignClient;
        this.locationFeignClient = locationFeignClient;
        this.apiKey = apiKey;
    }

    public Forecast getForecast(String longitude, String latitude, String units) {
        return weatherFeignClient.getForecast(longitude, latitude, units, apiKey);
    }

    public List<Location> getLocation(String locationName, int limit) {
        return locationFeignClient.getLocation(locationName, String.valueOf(limit), apiKey);
    }

}
