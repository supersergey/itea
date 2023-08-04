package com.example.demo.jsonprocessing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;

@JsonDeserialize(using = WeatherDtoResponseDeserializer.class)
public record WeatherDtoResponse(
    String name,
    BigDecimal lat,
    BigDecimal lon,
    String countryCode,
    String localCityName)
{ }
