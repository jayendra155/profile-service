package com.user.profile.api.controller.spec;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import com.user.profile.model.commons.SubscriptionStatus;
import com.user.profile.model.dto.SubscriptionDTO;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
public interface SubscriptionSpec {

    ResponseEntity<Collection<SubscriptionDTO>> getAllSubscriptionsForProfile(Long profileId, SubscriptionStatus status);
}
