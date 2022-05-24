package com.user.profile.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.user.profile.core.config.KafkaProducerConfig;
import com.user.profile.core.repository.jpa.JpaRepositoryAutoConfiguration;
import com.user.profile.core.repository.mongo.MongoRepositoryAutoConfig;

/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackageClasses = { WorkerApplication.class, KafkaProducerConfig.class,
        JpaRepositoryAutoConfiguration.class, MongoRepositoryAutoConfig.class }, exclude = {
        UserDetailsServiceAutoConfiguration.class })
public class WorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
    }
}
