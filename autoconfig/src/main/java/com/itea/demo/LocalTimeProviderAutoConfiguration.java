package com.itea.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalTimeProviderAutoConfiguration {
    @Bean
    public LocalTimeProvider getLocalTimeProvider() {
        return new LocalTimeProvider();
    }
}
