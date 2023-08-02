package com.example.demo.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
@Slf4j
@ConditionalOnProperty(prefix = "demo", name = "feign", havingValue = "false")
public class OpenWeatherServiceRestImpl implements OpenWeatherService{
    private final String BASE_URL;
    private final String GEO_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    public OpenWeatherServiceRestImpl(@Value("${openweathermap.base.url}") String baseUrl,
                                      @Value("${openweathermap.base.location.url}") String baseLocationUrl) {
        this.BASE_URL = baseUrl;
        this.GEO_URL = baseLocationUrl;
    }
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

    public Coordinates getCoordinates(String name, int limit){
        var uri = UriComponentsBuilder
                .fromUriString(GEO_URL)
                .queryParam("q", name)
                .queryParam("limit", String.valueOf(limit))
                .queryParam("apiKey", apiKey)
                .build()
                .toUriString();
        try {
            var coordinates = restTemplate.getForObject(uri, Coordinates[].class);
            if(coordinates != null) {
                return Arrays.stream(coordinates).findFirst().orElse(null);
            }
            else return null;
        }
        catch (HttpClientErrorException ex){
            log.error("Error code: {}, message: {}", ex.getStatusCode(), ex.getMessage());
            return null;
        }
    }
}
