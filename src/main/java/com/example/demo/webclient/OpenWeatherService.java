package com.example.demo.webclient;

public interface OpenWeatherService {
    Forecast getForecast(String longitude, String latitude, String units);
}
