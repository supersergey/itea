package com.itea.demo;

import java.time.Instant;

public class LocalTimeProvider {
    public Instant getCurrentTime() {
        return Instant.now();
    }
}
