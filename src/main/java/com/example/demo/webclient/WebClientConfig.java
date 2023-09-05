package com.example.demo.webclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Bean("weatherClient")
    public OpenWeatherFeignClient getFeignClient(
            @Qualifier("webClientObjectMapper") ObjectMapper objectMapper,
            WebClientConfigurationProperties properties) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .target(OpenWeatherFeignClient.class, properties.getBaseUrl().toString());
    }

    @Bean("locationClient")
    public OpenWeatherFeignClient getFeignClientForLocation(
            @Qualifier("webClientObjectMapper") ObjectMapper objectMapper,
            WebClientConfigurationProperties properties,
            ErrorDecoder errorDecoder
    ) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .errorDecoder(errorDecoder)
                .target(OpenWeatherFeignClient.class, properties.getBaseLocationUrl().toString());
    }

    @Bean
    @Qualifier("webClientObjectMapper")
    public ObjectMapper webClientObjectMapper() {
        return new ObjectMapper()
                .registerModules(new JavaTimeModule(), new Jdk8Module())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    public ErrorDecoder locationFeignClientErrorDecoder() {
        return new OpenWeatherFeignClientErrorDecoder();
    }
}
