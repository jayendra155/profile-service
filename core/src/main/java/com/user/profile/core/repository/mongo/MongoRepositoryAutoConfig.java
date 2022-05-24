package com.user.profile.core.repository.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Slf4j
@Configuration
@EnableMongoRepositories(basePackageClasses = { SubscriptionUpdateRepository.class })
@EnableMongoAuditing
public class MongoRepositoryAutoConfig {
}
