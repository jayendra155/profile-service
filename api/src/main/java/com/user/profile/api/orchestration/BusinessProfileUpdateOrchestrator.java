package com.user.profile.api.orchestration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.profile.api.orchestration.interfaces.Action;
import com.user.profile.api.service.BusinessProfileUpdateRequestEventingService;
import com.user.profile.api.service.BusinessProfileUpdateRequestPersistentService;
import com.user.profile.api.service.BusinessProfileUpdateValidationService;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Service
public class BusinessProfileUpdateOrchestrator
        implements Action<BusinessProfileUpdateRequestDTO, BusinessProfileUpdateResponseDTO> {

    @Autowired
    private BusinessProfileUpdateValidationService validationService;

    @Autowired
    private BusinessProfileUpdateRequestPersistentService persistentService;

    @Autowired
    private BusinessProfileUpdateRequestEventingService eventService;

    @Override
    public BusinessProfileUpdateResponseDTO execute(final BusinessProfileUpdateRequestDTO input) {
        return validationService
                .then(persistentService)
                .then(eventService)
                .execute(input);
    }
}
