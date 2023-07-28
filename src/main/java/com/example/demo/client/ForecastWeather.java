package com.example.demo.client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForecastWeather {
    private String main;
    private String description;
}
