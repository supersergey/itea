package com.example.demo.configuration;

import com.example.demo.webclient.openfeignclient.OpenWeatherApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherApiConfig {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String BASE_LOCATION_URL = "https://api.openweathermap.org/geo/1.0/direct";

    @Bean("weatherClient")
    public OpenWeatherApi getFeignClientForWeatherForecast() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherApi.class, BASE_URL);
    }

    @Bean("locationClient")
    public OpenWeatherApi getFeignClientForLocation() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherApi.class, BASE_LOCATION_URL);
    }


}
