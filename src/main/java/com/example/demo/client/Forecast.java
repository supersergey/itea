package com.example.demo.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Forecast {
    private List<ForecastDetail> list;
}
