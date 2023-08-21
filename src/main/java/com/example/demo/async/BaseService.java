package com.example.demo.async;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@AllArgsConstructor
@Slf4j
public class BaseService {
    private AsyncService asyncService;

    @SneakyThrows
    public String doSomething() {
        log.info("Starting BaseService");
        Future<String> result = asyncService.doSomething();
        while (!result.isDone()) {
            log.info("Waiting...");
            Thread.sleep(500);
        }
        log.info("Execution result, {}", result.get());
        return result.get();
    }
}
