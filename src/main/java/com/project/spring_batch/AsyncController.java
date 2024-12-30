package com.project.spring_batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping(value = "/single-thread")
    public String singleThreadTest() {
        log.info("Single Thread Test");

        for (int i=0;i<10;i++) {
            asyncService.singleThread();
        }

        log.info("Single Thread Test Finished");

        return "SUCCESS";
    }

    @GetMapping(value = "/multi-thread")
    public String multiThreadTest() {
        log.info("Multi Thread Test");

        for (int i=0;i<10;i++) {
            asyncService.multiThread();
        }

        log.info("Multi Thread Test Finished");

        return "SUCCESS";
    }
}
