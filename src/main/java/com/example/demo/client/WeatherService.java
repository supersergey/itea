package com.example.demo.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final WeatherClient weatherClient;
    private final RestTemplate restTemplate = new RestTemplate();

    private final WebClientFeign webClientFeign;

    public WeatherService(WeatherClient weatherClient, WebClientFeign webClientFeign) {
        this.weatherClient = weatherClient;
        this.webClientFeign = webClientFeign;
    }

    public Forecast getForecast(String latitude, String longitude, String units, String apiKey) {
//        var request = UriComponentsBuilder.fromUriString("https://api.openweathermap.org/data/2.5/forecast")
//                .queryParam("lon", longitude)
//                .queryParam("lat", latitude)
//                .queryParam("units", units)
//                .queryParam("apiKey", apiKey)
//                .build().encode().toUriString();
//
//        return restTemplate.getForEntity(request, Forecast.class).getBody();

        return webClientFeign.getForecast(longitude, latitude, units, apiKey);

//        return weatherClient.getForecast(
//            latitude, longitude, units, apiKey
//        );
    }
}
