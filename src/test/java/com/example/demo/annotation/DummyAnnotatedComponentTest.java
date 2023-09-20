package com.example.demo.annotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DummyAnnotatedComponent.class, TimeMeasureAspect.class, AopConfig.class})
@ExtendWith(OutputCaptureExtension.class)
class DummyAnnotatedComponentTest {

    @Autowired
    private DummyAnnotatedComponent dummyAnnotatedComponent;

    @Test
    void doSomething(CapturedOutput capturedOutput) throws Exception {
        dummyAnnotatedComponent.doSomething();
        assertThat(capturedOutput.getAll()).contains("Execution duration:");
    }
}