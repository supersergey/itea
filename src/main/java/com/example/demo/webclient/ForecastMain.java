package com.example.demo.webclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ForecastMain {
    private String temp;
    @JsonProperty("feels_like")
    private String feelsLike;
}
