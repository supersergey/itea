package com.example.demo.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ScheduledTask {
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void doSomething() {
        log.info("Scheduled task running...");
    }
}
