package com.user.profile.core.repository.jpa;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.user.profile.model.domain.jpa.SubscriptionEntityAutoConfiguration;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */

@Slf4j
@Configuration
@Import(SubscriptionEntityAutoConfiguration.class)
@EnableJpaRepositories
public class JpaRepositoryAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("Initializing {}", this.getClass().getSimpleName());
    }
}
