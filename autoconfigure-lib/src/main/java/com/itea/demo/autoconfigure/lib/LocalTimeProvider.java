package com.itea.demo.autoconfigure.lib;

import java.time.Instant;

public class LocalTimeProvider {
    public Instant getLocalTime() {
        return Instant.now();
    }
}
