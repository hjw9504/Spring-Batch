package com.project.spring_batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    public void singleThread() {
        try {
            log.info("Start singleThread");
            Thread.sleep(5000);
            log.info("End singleThread");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async("taskExecutor")
    public void multiThread() {
        try {
            log.info("Start multiThread");
            Thread.sleep(5000);
            log.info("End multiThread");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
