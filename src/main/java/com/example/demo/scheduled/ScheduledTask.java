package com.example.demo.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ScheduledTask {
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void doOnSchedule() {
        log.info("This is a scheduled task");
    }
}
