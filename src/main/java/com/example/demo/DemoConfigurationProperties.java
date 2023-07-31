package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@ConfigurationProperties(prefix = "demo")
@Value
@Validated
public class DemoConfigurationProperties {
    Boolean rest;
    Boolean feign;
    DataSize size;
    Duration frequency;
    @Size(min = 32, max = 32, message = "Size must be between 32 characters")
    String apiKey;
}
