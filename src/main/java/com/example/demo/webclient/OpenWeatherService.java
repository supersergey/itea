package com.example.demo.webclient;

public interface OpenWeatherService {
    public Forecast getForecast(String longitude, String latitude, String units);
}
