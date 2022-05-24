package com.user.profile.model.dto;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Data
@Validated
public class BusinessProfileUpdateRequestDTO {

    @NotNull
    private Long id;
    //private Long ownerId;
    private Set<AddressDTO> addresses;
    private String companyName;
    private String legalName;
    @Email
    private String email;
    private String website;
    private String taxIdentifierDocName;
    private String taxIdentifierDocId;

}
