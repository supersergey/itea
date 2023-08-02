package com.example.demo.async;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    @Async
    @SneakyThrows
    CompletableFuture<String> doSomethingAsynchronously() {
        Thread.sleep(10000);
        return CompletableFuture.completedFuture("result");
    }
}
