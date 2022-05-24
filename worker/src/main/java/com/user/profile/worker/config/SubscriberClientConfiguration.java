package com.user.profile.worker.config;

import java.util.Collections;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import com.user.profile.model.commons.SubscriptionApplication;
import com.user.profile.worker.web.ClientProperties;
import lombok.Setter;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 01/05/22
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "subscription.apis.validation")
public class SubscriberClientConfiguration {

    @Setter
    private Map<SubscriptionApplication, ClientProperties> applications;

    public Map<SubscriptionApplication, ClientProperties> getApplications() {
        return Collections.unmodifiableMap(this.applications);
    }

}
