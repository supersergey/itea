package com.example.demo.jsonprocessing;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class WeatherDtoResponseDeserializer extends JsonDeserializer<WeatherDtoResponse> {
    @Override
    public WeatherDtoResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var node = p.readValueAsTree();
        return new WeatherDtoResponse(
                node.get("name").toString(),
                new BigDecimal(node.get("lat").toString()),
                new BigDecimal(node.get("lon").toString()),
                node.get("country").toString(),
                node.get("local_names").get("uk").toString()
        );
    }
}
