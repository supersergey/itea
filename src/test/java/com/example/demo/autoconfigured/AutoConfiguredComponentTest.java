package com.example.demo.autoconfigured;

import com.itea.demo.autoconfigure.lib.LocalTimeProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutoConfiguredComponentTest {
    @Autowired
    private AutoConfiguredComponent component;
    @Autowired
    private LocalTimeProvider localTimeProvider;

    @Test
    void shouldResolveAutoConfiguredComponent() {
        component.doSomething();
    }
}