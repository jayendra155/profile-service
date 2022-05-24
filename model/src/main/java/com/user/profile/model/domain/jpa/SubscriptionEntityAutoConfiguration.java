package com.user.profile.model.domain.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */

@Slf4j
@Configuration
@EntityScan(basePackageClasses = { SubscriptionEntityAutoConfiguration.class })
public class SubscriptionEntityAutoConfiguration {

}
