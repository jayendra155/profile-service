package com.user.profile.model.dto;

import java.util.Set;
import org.springframework.hateoas.RepresentationModel;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 19/04/22
 */
@Data
public class BusinessProfileDTO extends RepresentationModel<BusinessProfileDTO> {

    private Long id;
    private Long ownerId;
    private Set<AddressDTO> addresses;
    private Set<SubscriptionDTO> subscriptions;
    private String companyName;
    private String legalName;
    private String email;
    private String website;
    private String taxIdentifierDocName;
    private String taxIdentifierDocId;
}
