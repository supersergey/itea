package com.example.demo.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class MeasureDurationAspect {
    @Around("@annotation(MeasureDuration)")
    public Object logExecutionDuration(ProceedingJoinPoint joinPoint) throws Throwable {
        var start = Instant.now();
        var result = joinPoint.proceed();
        log.info("Execution duration: {}", Duration.between(start, Instant.now()).toMillis());
        return result;
    }
}
