package com.example.demo.webclient;

import org.springframework.beans.factory.annotation.Qualifier;
import feign.FeignException;
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
                                       WebClientConfigurationProperties properties) {
        this.weatherFeignClient = weatherFeignClient;
        this.locationFeignClient = locationFeignClient;
        this.apiKey = properties.getApiKey();
    }

    public Forecast getForecast(String longitude, String latitude, String units) {
        try {
            return weatherFeignClient.getForecast(longitude, latitude, units, apiKey);
        } catch (FeignException ex) {
            return null;
        }
    }

    public List<Location> getLocation(String locationName, int limit) {
        try {
            return locationFeignClient.getLocation(locationName, String.valueOf(limit), apiKey);
        } catch (FeignException e) {
            return null;
        }
    }

}
