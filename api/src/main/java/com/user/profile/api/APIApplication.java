package com.user.profile.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import com.user.profile.core.config.KafkaProducerConfig;
import com.user.profile.core.repository.jpa.JpaRepositoryAutoConfiguration;
import com.user.profile.core.repository.mongo.MongoRepositoryAutoConfig;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@SpringBootApplication(scanBasePackageClasses = { APIApplication.class, KafkaProducerConfig.class,
        JpaRepositoryAutoConfiguration.class, MongoRepositoryAutoConfig.class }, exclude = {
        UserDetailsServiceAutoConfiguration.class })
public class APIApplication {

    public static void main(String[] args) {
        SpringApplication.run(APIApplication.class, args);
    }
}
