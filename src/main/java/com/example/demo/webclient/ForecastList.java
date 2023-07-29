package com.example.demo.webclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ForecastList {
    @JsonProperty(value = "dt_txt")
    private String timeStamp;
    private ForecastMain main;
    private List<ForecastWeather> weather;
}
