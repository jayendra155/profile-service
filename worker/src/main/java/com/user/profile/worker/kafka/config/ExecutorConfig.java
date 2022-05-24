package com.user.profile.worker.kafka.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Configuration
public class ExecutorConfig {

    @Autowired
    private BeanFactory beanFactory;

    private static AtomicInteger counter = new AtomicInteger(0);

    @Bean(name = "profileUpdateRequestExecutor")
    public Executor profileUpdateRequestExecutor() {
        return new TraceableExecutorService(beanFactory, Executors.newFixedThreadPool(3, r -> {
            Thread thread = new Thread(r);
            thread.setName("BPURExecutor-" + counter.incrementAndGet());
            return thread;
        }));
    }
}
