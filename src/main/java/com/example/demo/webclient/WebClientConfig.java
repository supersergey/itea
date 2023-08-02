package com.example.demo.webclient;


import com.example.demo.webclient.feign.OpenWeatherApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {
    private final String BASE_URL;
    private final String GEO_URL;
    public WebClientConfig(@Value("${openweathermap.base.url}") String baseUrl,
                           @Value("${openweathermap.geo.url}") String geoUrl) {
        this.BASE_URL = baseUrl;
        this.GEO_URL = geoUrl;
    }
    @Bean("weatherClient")
    public OpenWeatherApi getFeignClient(ObjectMapper objectMapper) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .target(OpenWeatherApi.class, BASE_URL);
    }
    @Bean("locationClient")
    public OpenWeatherApi getFeignClientForLocation() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherApi.class, GEO_URL);
    }
}
