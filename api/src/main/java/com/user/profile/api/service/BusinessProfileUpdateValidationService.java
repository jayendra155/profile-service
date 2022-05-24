package com.user.profile.api.service;

import org.springframework.stereotype.Service;
import com.user.profile.api.orchestration.interfaces.Action;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.mapper.BusinessProfileMapper;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Service
public class BusinessProfileUpdateValidationService implements
        Action<BusinessProfileUpdateRequestDTO, BusinessProfileUpdateRequestDoc> {

    @Override
    public BusinessProfileUpdateRequestDoc execute(final BusinessProfileUpdateRequestDTO input) {
        //TODO: implement custom validation which are required as per use case
        return BusinessProfileMapper.MAPPER_INSTANCE.fromUpdateRequestDTO(input);
    }
}
