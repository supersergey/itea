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
public class TimeMeasureAspect {
    @Around("@annotation(TimeMeasured)")
    public Object logExecutionDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        var start = Instant.now();
        var result = proceedingJoinPoint.proceed();
        var finish = Instant.now();
        log.info("Execution duration: {}", Duration.between(start, finish).toMillis());
        return result;
    }
}
