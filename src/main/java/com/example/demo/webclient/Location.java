package com.example.demo.webclient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LocationDeserializer.class)
public record Location(
        String name,
        String longitude,
        String latitude,
        String localName,
        String country
) {
}
