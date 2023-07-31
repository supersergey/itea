//package com.example.demo.webclient;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.Feign;
//import feign.gson.GsonDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebClientConfig {
//    @Bean
//    public OpenWeatherFeignClient getFeignClient(ObjectMapper objectMapper) {
//        return Feign.builder()
//                .decoder(new GsonDecoder())
//                .target(OpenWeatherFeignClient.class, "https://api.openweathermap.org/data/2.5/forecast");
//    }
//}
