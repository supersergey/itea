package com.example.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Data
@NoArgsConstructor
public class ForecastDetail {
    @JsonProperty("dt")
    @JsonDeserialize(using = LongToLocalDateTimeDeserialer.class)
    private LocalDateTime timeStamp;
    private ForecastMain main;
    private List<ForecastWeather> weather;

    static class LongToLocalDateTimeDeserialer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            var unixTs = p.readValueAs(Long.class);
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTs), TimeZone.getDefault().toZoneId());
        }
    }
}
