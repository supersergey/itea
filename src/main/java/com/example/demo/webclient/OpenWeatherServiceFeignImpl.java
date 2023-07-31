package com.example.demo.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("feign")
@Primary
public class OpenWeatherServiceFeignImpl implements OpenWeatherService {
    private final OpenWeatherFeignClient client;

    private final String apiKey;

    public OpenWeatherServiceFeignImpl(OpenWeatherFeignClient client,
                                       @Value("${openweathermap.apiKey}") String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    public Forecast getForecast(String longitude, String latitude, String units) {
        return client.getForecast(longitude, latitude, units, apiKey);
    }

}
