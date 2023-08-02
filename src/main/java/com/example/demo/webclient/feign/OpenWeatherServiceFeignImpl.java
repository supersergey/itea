package com.example.demo.webclient.feign;

import com.example.demo.webclient.Coordinates;
import com.example.demo.webclient.Forecast;
import com.example.demo.webclient.OpenWeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import feign.Feign;
import feign.jackson.JacksonDecoder;

@Service("feign")
@ConditionalOnProperty(prefix = "demo", name = "feign", havingValue = "true")
public class OpenWeatherServiceFeignImpl implements OpenWeatherService {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String GEO_URL = "https://api.openweathermap.org/geo/1.0/direct";
    private static final Feign.Builder openWeatherApiBuilder = Feign.builder()
            .decoder(new JacksonDecoder());

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    public Forecast getForecast(String longitude, String latitude, String units) {

        return openWeatherApiBuilder.target(OpenWeatherApi.class, BASE_URL)
                .getForecast(longitude, latitude, units, apiKey);
    }

    public Coordinates getCoordinates(String name, int limit) {
        return openWeatherApiBuilder.target(OpenWeatherApi.class, GEO_URL)
                .getCoordinates(name, String.valueOf(limit), apiKey)
                .stream()
                .findFirst()
                .get();
    }
}
