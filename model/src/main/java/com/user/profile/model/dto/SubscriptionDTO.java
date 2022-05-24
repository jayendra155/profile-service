package com.user.profile.model.dto;

import com.user.profile.model.commons.SubscriptionApplication;
import com.user.profile.model.commons.SubscriptionStatus;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 19/04/22
 */
@Data
public class SubscriptionDTO extends BaseDTO{

    private Long id;

    private Long businessProfileId;

    private SubscriptionApplication subscriptionServiceName;

    private SubscriptionStatus subscriptionStatus;
}
