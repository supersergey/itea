package com.example.demo.annotation;

import org.springframework.stereotype.Component;

@Component
public class TimeMeasuredComponent {
    @MeasureDuration
    public void doSomething() throws Exception {
        Thread.sleep(1000);
    }
}
