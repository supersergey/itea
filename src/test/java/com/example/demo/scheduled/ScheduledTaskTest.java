package com.example.demo.scheduled;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest(classes = ScheduledTask.class)
@EnableScheduling
class ScheduledTaskTest {

    @Test
    void doSomething() throws Exception {
        Thread.sleep(5000);
    }
}