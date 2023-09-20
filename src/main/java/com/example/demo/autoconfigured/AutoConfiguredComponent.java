package com.example.demo.autoconfigured;

import com.itea.demo.autoconfigure.lib.LocalTimeProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutoConfiguredComponent {
    private final LocalTimeProvider localTimeProvider;

    public void doSomething() {
        System.out.println(localTimeProvider.getLocalTime());
    }
}
