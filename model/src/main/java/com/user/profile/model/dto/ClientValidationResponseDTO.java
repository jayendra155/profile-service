package com.user.profile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 02/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientValidationResponseDTO {

    private Status response;


    public enum Status {
        APPROVED, RETRY_REQUIRED, DENIED;
    }

}
