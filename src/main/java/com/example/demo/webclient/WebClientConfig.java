package com.example.demo.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Bean
    public OpenWeatherFeignClient getFeignClient(
            ObjectMapper objectMapper,
            WebClientConfigurationProperties properties) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .target(OpenWeatherFeignClient.class, properties.getBaseUrl().toString());
    }
}
