package com.example.demo.jsonprocessing;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public record DemoMetaData(String desc) {
}
