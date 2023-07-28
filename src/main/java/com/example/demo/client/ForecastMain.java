package com.example.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForecastMain {
    private String temp;
    @JsonProperty("feels_like")
    private String feelsLike;
}
