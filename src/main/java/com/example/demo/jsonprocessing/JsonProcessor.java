package com.example.demo.jsonprocessing;

import com.example.demo.controller.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class JsonProcessor {
    private final ObjectMapper mapper;

    public JsonProcessor() {
        this.mapper = new ObjectMapper();
        this.mapper
                .registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module());
    }

    public String toJsonString(User user) throws Exception {
        return mapper.writeValueAsString(user);
    }

    public String toJsonString(DemoDto demoDto) throws Exception {
        return mapper.writeValueAsString(demoDto);
    }

    public static void main(String[] args) throws Exception {
        var jsonProcessor = new JsonProcessor();
        System.out.println(jsonProcessor.toJsonString(
                        new DemoDto("id", LocalDateTime.now(), new DemoMetaData("meta"))
                )
        );
    }
}
