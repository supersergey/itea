package com.example.demo.webclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.unit.DataSize;

import java.net.URL;
import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 8889)
class OpenWeatherServiceNoSpringTest {

    private OpenWeatherService openWeatherService;

    @BeforeEach
    void setUp() throws Exception {
        var config = new WebClientConfig();
        var properties = new WebClientConfigurationProperties(
                new URL("http://localhost:8889/data/2.5/forecast"),
                new URL("http://localhost:8889/geo/1.0/direct"),
                "apiKey", Duration.ZERO, DataSize.ofBytes(0)
                );
        openWeatherService = new OpenWeatherServiceFeignImpl(
                config.getFeignClient(config.webClientObjectMapper(), properties),
                config.getFeignClientForLocation(config.webClientObjectMapper(), properties, new OpenWeatherFeignClientErrorDecoder()),
                properties
        );
    }

    @Test
    void getForecast() {
        stubFor(get(
                urlPathEqualTo("/data/2.5/forecast"))
                .willReturn(
                        ok().withBodyFile("openweather_success.json")
                                .withFixedDelay((int) Duration.ofSeconds(5).toMillis())
                )
        );

        var actual = openWeatherService.getForecast("30.45",
                "50.45",
                "metric").getList().get(0).getMain();

        assertThat(actual.getTemp()).isEqualTo("22.00");
        assertThat(actual.getFeelsLike()).isEqualTo("20.00");
    }

    @Test
    void shouldReturnNullOn500Error() {
        stubFor(get(
                urlPathEqualTo("/data/2.5/forecast"))
                .willReturn(
                        aResponse()
                                .withStatus(500)
                                .withBody("Internal Server error")
                )
        );

        var actual = openWeatherService.getForecast("30.45",
                "50.45",
                "metric");

        assertThat(actual).isNull();
    }
}

