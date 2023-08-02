package com.example.demo.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Slf4j
public class BaseService {
    private final AsyncService asyncService;

    void doSomething() {
        log.info("Calling base service");
        var future = asyncService.doSomethingAsynchronously();
        try {
            while (!future.isDone()) {
                log.info("Waiting for result");
                Thread.sleep(500);
            }
            log.info("Received result: {}", future.get());
        } catch (ExecutionException | InterruptedException ex) {
            log.error(ex.getMessage());
        }
    }
}
