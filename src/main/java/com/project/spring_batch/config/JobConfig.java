package com.project.spring_batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfig {

    @Bean
    public Job SpringJob(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        return new JobBuilder("springJob", jobRepository)
                .start(initJob(transactionManager, jobRepository))
                .build();
    }

    @Bean
    public Step initJob(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        log.info("Init Job");
        return new StepBuilder("initJob", jobRepository)
                .tasklet(firstJob(), transactionManager)
                .build();
    }

    public Tasklet firstJob() {
        return (contribution, chunkContext) -> {
            log.info("First Job");
            return RepeatStatus.FINISHED;
        };
    }
}
