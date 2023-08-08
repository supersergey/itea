package com.example.demo.webclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LocationDeserializerTest.class)
class LocationDeserializerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("classpath:/openWeatherLocation.json")
    private File input;

    @Test
    void shouldUseCustomDeserializer() throws IOException {
        var a = new ArrayList<String>();
        var b = new ArrayList<Integer>(); // type erasure
        assertThat(a.getClass()).isEqualTo(b.getClass());
        var location = objectMapper.readValue(
                input,
                new TypeReference<List<Location>>() {}
        );
        System.out.println(location);
    }
}