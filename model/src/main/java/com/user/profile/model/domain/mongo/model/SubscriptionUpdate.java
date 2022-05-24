package com.user.profile.model.domain.mongo.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import com.user.profile.model.commons.SubscriptionApplication;
import com.user.profile.model.commons.SubscriptionUpdateType;
import com.user.profile.model.domain.mongo.Status;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Data
@NoArgsConstructor
@Document(collection = "subscriptionUpdate")
public class SubscriptionUpdate {

    @Id
    private ObjectId id;

    private SubscriptionUpdateType updateType;

    private Long businessProfileId;

    private SubscriptionApplication application;

    private String traceId;

    private Status updateStatus;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private static final String COLLECTION_NAME = SubscriptionUpdate.class.getAnnotation(Document.class).collection();

}
