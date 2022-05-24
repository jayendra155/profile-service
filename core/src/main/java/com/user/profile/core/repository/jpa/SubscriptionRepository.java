package com.user.profile.core.repository.jpa;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import com.user.profile.model.commons.SubscriptionStatus;
import com.user.profile.model.domain.jpa.SubscriptionEntity;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    Set<SubscriptionEntity> findByBusinessProfileIdAndSubscriptionStatus(Long businessProfileId,
            SubscriptionStatus status);
}
