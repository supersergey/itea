package com.example.demo.json;

import com.example.demo.controller.dto.User;
import com.example.demo.webclient.WebClientConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@SpringBootTest(classes = {ObjectMapper.class})
@Import(WebClientConfig.class)
public class JsonTest {
    @Autowired
//    @Qualifier("myObjectMapper")
    ObjectMapper objectMapper;

    @Test
    void shouldPrintJsonString() throws JsonProcessingException {
        var user = new User("Taras", "Shevchenko", null);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(objectMapper.writeValueAsString(user));
    }

    @Test
    void shouldSkipFields() throws JsonProcessingException {
        var user = new User("Taras", "Shevchenko", "POET");
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(objectMapper.writeValueAsString(user));
    }

    @Test
    void shouldSkipFieldsOfType() throws JsonProcessingException {
        var dummyData = new DummyData("id", new DummyMeta("metaData"), LocalDateTime.now());
        System.out.println(objectMapper.writeValueAsString(dummyData));
    }
}
