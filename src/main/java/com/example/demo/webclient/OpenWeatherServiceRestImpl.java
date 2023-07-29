package com.example.demo.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class OpenWeatherServiceRestImpl {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    public Forecast getForecast(String longitude, String latitude, String units) {
        var uri = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("units", units)
                .queryParam("apiKey", apiKey)
                .build()
                .toUriString();

        try {
            return restTemplate.getForObject(uri, Forecast.class);
        } catch (HttpClientErrorException ex) {
            log.error("Error code: {}, message: {}", ex.getStatusCode(), ex.getMessage());
            return null;
        }
    }
}
