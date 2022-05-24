package com.user.profile.model.dto;

import java.util.Date;
import com.user.profile.model.domain.mongo.Status;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Data
public class BusinessProfileUpdateResponseDTO {

    private String id;
    private Long businessProfileId;
    private Status status;
    private Date requestTime;
    private Date lastUpdatedOn;
}
