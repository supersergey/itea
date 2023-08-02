package com.example.demo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import java.net.URL;
import java.time.Duration;

@ConfigurationProperties(prefix = "demo")
@Value
@Validated
public class DemoConfigurationProperties {
    @NotNull
    Boolean feign;
    @NotNull
    DataSize size;
    @NotNull
    Duration frequency;
    @NotNull @Size(min = 32, max = 32, message = "Size must be between 32 characters")
    String apiKey;
    @NotNull
    URL baseUrl;
}
