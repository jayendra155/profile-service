package com.user.profile.core.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@Configuration
public class ExecutorsConfig {

    private static AtomicInteger kafkaExecutorCounter = new AtomicInteger(0);

    @Bean("kafkaExecutor")
    public ExecutorService kafkaExecutor(BeanFactory beanFactory) {
        return new TraceableExecutorService(beanFactory, Executors.newFixedThreadPool(3, r -> {
            Thread thread = new Thread(r);
            thread.setName("kafkaExecutor-" + kafkaExecutorCounter.incrementAndGet());
            return thread;
        }));
    }
}
