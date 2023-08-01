package com.example.demo.webclient;

import java.util.List;

public interface OpenWeatherService {
    Forecast getForecast(String longitude, String latitude, String units);

    List<Location> getLocation(String locationName, int limit);
}
