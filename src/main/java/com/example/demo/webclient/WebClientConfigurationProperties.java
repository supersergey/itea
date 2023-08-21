package com.example.demo.webclient;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import java.net.URL;
import java.time.Duration;

@Value
@ConfigurationProperties(prefix = "openweathermap")
@Validated
public class WebClientConfigurationProperties {
    @NotNull URL baseUrl;
    @NotNull String apiKey;
    @NotNull Duration updateFrequency;
    @NotNull DataSize maxSize;
}
