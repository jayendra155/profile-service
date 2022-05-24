package com.user.profile.api.controller.spec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.user.profile.model.dto.BusinessProfileDTO;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 19/04/22
 */
public interface BusinessProfileSpec {

    ResponseEntity<BusinessProfileDTO> getBusinessProfile(@PathVariable Long id);

    ResponseEntity<BusinessProfileUpdateResponseDTO> updateOrCreateProfile(BusinessProfileUpdateRequestDTO request);

    ResponseEntity<Page<BusinessProfileUpdateResponseDTO>> fetchAllPendingUpdatesForProfile(Long profileId, Integer page,
            Integer size, String property, Sort.Direction direction);

    ResponseEntity deleteProfile(Long profileId);

    ResponseEntity<BusinessProfileUpdateResponseDTO> getUpdateByUpdateId(String id);
}
