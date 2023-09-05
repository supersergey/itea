package com.example.demo.webclient;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenWeatherFeignClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        int responseStatus = response.status();

        return switch (responseStatus) {
            case 500 -> {
                log.info("Error with response status " + responseStatus + " occurred");
                yield new RetryableException(
                        response.status(),
                        response.reason(),
                        response.request().httpMethod(),
                        null,
                        response.request()
                );
            }
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }
}
