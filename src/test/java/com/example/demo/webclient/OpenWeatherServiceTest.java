package com.example.demo.webclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {OpenWeatherServiceFeignImpl.class})
@EnableConfigurationProperties({WebClientConfigurationProperties.class})
@Import(WebClientConfig.class)
@ActiveProfiles("test")
@WireMockTest(httpPort = 8889)
class OpenWeatherServiceTest {

    @Autowired
    private OpenWeatherService openWeatherService;

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

