package com.example.demo.annotation;

import org.springframework.stereotype.Component;

@Component
public class DummyAnnotatedComponent {

    @TimeMeasured
    public void doSomething() throws InterruptedException {
        Thread.sleep(1000);
    }
}
