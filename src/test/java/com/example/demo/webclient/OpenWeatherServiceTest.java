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

@SpringBootTest(classes = {OpenWeatherServiceTest.class, OpenWeatherServiceFeignImpl.class})
@EnableConfigurationProperties(WebClientConfigurationProperties.class)
@Import({WebClientConfig.class})
@ActiveProfiles("test")
@WireMockTest(httpPort = 8889)
class OpenWeatherServiceTest {

    @Autowired
    private OpenWeatherService openWeatherService;

    @Test
    void shouldReturnSuccessfulResponse() {
        stubFor(get(urlPathEqualTo("/")).willReturn(aResponse()
                .withStatus(200)
                .withBodyFile("openweather_success_response.json")
                .withHeader("Content type", "application/json")
                .withFixedDelay((int) Duration.ofSeconds(2).toMillis())
        ));

        var actual = openWeatherService.getForecast("30.45",
                "50.45",
                "metric");

        assertThat(actual.getList().get(0).getMain().getFeelsLike()).isEqualTo("20.00");
        assertThat(actual.getList().get(0).getMain().getTemp()).isEqualTo("22.00");
    }

    @Test
    void shouldProcess500Response() {
        stubFor(get(urlPathEqualTo("/")).willReturn(aResponse()
                .withStatus(500)
                .withBody("Internal Server Error")
        ));

        var actual = openWeatherService.getForecast("30.45",
                "50.45",
                "metric");

        assertThat(actual).isNotNull();
    }
}
