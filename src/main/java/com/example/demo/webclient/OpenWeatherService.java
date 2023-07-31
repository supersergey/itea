package com.example.demo.webclient;

import java.util.List;

public interface OpenWeatherService {
    Forecast getForecast(String longitude, String latitude, String units);

    Coordinates getCoordinates(String locationName, int limit);
}
