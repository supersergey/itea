package com.example.demo.json;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public record DummyMeta(String id) {
}
