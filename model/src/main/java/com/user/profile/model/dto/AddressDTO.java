package com.user.profile.model.dto;

import com.user.profile.model.commons.AddressType;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 19/04/22
 */
@Data
public class AddressDTO extends BaseDTO {

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private AddressType type;
    private Long profileId;
}
