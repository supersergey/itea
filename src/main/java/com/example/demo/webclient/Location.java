package com.example.demo.webclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Location {

    private String name;

    @JsonProperty(value = "lat")
    private String latitude;

    @JsonProperty(value = "lon")
    private String longitude;

    private String country;
}
