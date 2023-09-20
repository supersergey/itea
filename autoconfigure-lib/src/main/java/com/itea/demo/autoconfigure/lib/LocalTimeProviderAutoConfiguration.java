package com.itea.demo.autoconfigure.lib;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalTimeProviderAutoConfiguration {
    @Bean
    public LocalTimeProvider localTimeProvider() {
        return new LocalTimeProvider();
    }

}
