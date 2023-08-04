package com.example.demo.jsonprocessing;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DemoDto(
        String id,
        @JsonFormat(pattern = "MM-dd-yy HH:mm:ss")
        LocalDateTime localDateTime,
        DemoMetaData metaData
) {
}
