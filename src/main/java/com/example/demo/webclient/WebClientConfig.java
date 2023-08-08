package com.example.demo.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {
    private final String BASE_LOCATION_URL;
    public WebClientConfig(@Value("${openweathermap.base.location.url}") String baseLocationUrl) {
    this.BASE_LOCATION_URL = baseLocationUrl;
    }

    @Bean("weatherClient")
    public OpenWeatherFeignClient getFeignClient(
            ObjectMapper objectMapper,
            WebClientConfigurationProperties properties) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .target(OpenWeatherFeignClient.class, properties.getBaseUrl().toString());
    }

    @Bean("locationClient")
    public OpenWeatherFeignClient getFeignClientForLocation() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherFeignClient.class, BASE_LOCATION_URL);

    }

//    @Bean
//    @Qualifier("myObjectMapper")
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper().registerModules(new JavaTimeModule(), new Jdk8Module());
//
//    }
}
