package com.user.profile.core.repository.mongo;

import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.user.profile.model.domain.mongo.Status;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
public interface BusinessProfileUpdateRequestRepository extends MongoRepository<BusinessProfileUpdateRequestDoc,
        String> {

    Page<BusinessProfileUpdateRequestDoc> findByBusinessProfileIdAndStatusIn(Long profileId, Set<Status> status,
            Pageable page);



}
