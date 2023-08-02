package com.example.demo.async;

import com.example.demo.scheduled.ScheduledTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest(classes = {BaseService.class, AsyncService.class, ScheduledTask.class})
@EnableAsync
@EnableScheduling
class BaseServiceTest {
    @Autowired
    private BaseService baseService;

    @Test
    void doSomething() {
        baseService.doSomething();
    }
}