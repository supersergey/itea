package com.example.demo.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public record DummyData(
        @JsonProperty("data_id")
        String dataId,
        DummyMeta metaData,
        //...
        @JsonFormat(pattern = "dd-MMM-yy HH:mm:ss")
        LocalDateTime localDateTime
        ) {
}
