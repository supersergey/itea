package com.example.demo.annotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TimeMeasuredComponent.class, MeasureDurationAspect.class})
@EnableAspectJAutoProxy
@ExtendWith(OutputCaptureExtension.class)
class TimeMeasuredComponentTest {
    @Autowired
    private TimeMeasuredComponent timeMeasuredComponent;

    @Test
    void doSomething(CapturedOutput output) throws Exception {
        timeMeasuredComponent.doSomething();
        assertThat(output.getAll()).contains("Execution duration");
    }
}