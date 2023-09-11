package com.example.demo.webclient;

import com.example.demo.webclient.feign.OpenWeatherServiceFeignImpl;
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
                config.getFeignClientForLocation(config.webClientObjectMapper(), properties),
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

    @Test
    void getCoordinate() {
        stubFor(get(
                urlPathEqualTo("/geo/1.0/direct"))
                .willReturn(
                        ok().withBodyFile("openweather_location_success.json"))
        );

        var actual = openWeatherService.getCoordinates("Kyiv", 1).get(0);
        String actualLatitude = actual.latitude();
        String actualLongitude = actual.longitude();

        assertThat(actualLatitude).isEqualTo("50.45");
        assertThat(actualLongitude).isEqualTo("30.52");
    }

    @Test
    void shouldReturnNullOn500ErrorCoordinate() {
        stubFor(get(
                urlPathEqualTo("/geo/1.0/direct"))
                .willReturn(
                        aResponse()
                                .withStatus(500)
                                .withBody("Internal Server error")
                )
        );

        var actual = openWeatherService.getCoordinates("Kyiv", 1);

        assertThat(actual).isNull();
    }
}

