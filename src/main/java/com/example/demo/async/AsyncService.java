package com.example.demo.async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncService {
    @SneakyThrows
    @Async
    public Future<String> doSomething() {
        log.info("Starting Async service");
        Thread.sleep(2000);
        log.info("Completing Async service");
        return CompletableFuture.completedFuture("result");
    }
}
