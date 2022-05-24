package com.user.profile.model.domain.mongo.model;

import java.util.Date;
import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import com.user.profile.model.domain.mongo.Status;
import com.user.profile.model.dto.AddressDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Data
@NoArgsConstructor
@Document
public class BusinessProfileUpdateRequestDoc {

    @Id
    private ObjectId id;

    private Long businessProfileId;
    private Set<AddressDTO> addresses;
    private String companyName;
    private String legalName;
    private String email;
    private String website;
    private String taxIdentifierDocName;
    private String taxIdentifierDocId;
    private Status status = Status.RECEIVED;
    private String traceId;
    @CreatedDate
    private Date requestTime;
    @LastModifiedDate
    private Date lastUpdatedOn;

}
