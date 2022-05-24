package com.user.profile.core.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.user.profile.model.domain.mongo.model.SubscriptionUpdate;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
public interface SubscriptionUpdateRepository extends MongoRepository<SubscriptionUpdate, String> {

}

