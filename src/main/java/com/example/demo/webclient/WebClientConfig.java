package com.example.demo.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String BASE_LOCATION_URL = "https://api.openweathermap.org/geo/1.0/direct";
    @Bean("weatherClient")
    public OpenWeatherFeignClient getFeignClient(ObjectMapper objectMapper) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .target(OpenWeatherFeignClient.class, BASE_URL);
    }

    @Bean("locationClient")
    public OpenWeatherFeignClient getFeignClientForLocation() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherFeignClient.class, BASE_LOCATION_URL);
    }
}
