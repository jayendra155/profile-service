package com.user.profile.worker.service.processors;

import static com.user.profile.model.mapper.BusinessProfileMapper.MAPPER_INSTANCE;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.user.profile.core.repository.jpa.BusinessProfileRepository;
import com.user.profile.core.repository.mongo.BusinessProfileUpdateRequestRepository;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.worker.service.BusinessProfileService;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Component
public class BusinessProfileUpdateRequestPersistentProcessor implements Processor {

    @Autowired
    private BusinessProfileService service;

    @Autowired
    private BusinessProfileUpdateRequestRepository updateRequestRepository;

    @Override
    public void process(final Exchange exchange) throws Exception {
        BusinessProfileUpdateRequestDoc document = exchange.getMessage().getBody(BusinessProfileUpdateRequestDoc.class);
        BusinessProfileEntity businessProfileEntity = MAPPER_INSTANCE.fromDocumentToEntity(document);
        businessProfileEntity = service.updateEntity(businessProfileEntity);
        updateRequestRepository.save(document);
        exchange.getIn().setBody(businessProfileEntity);

    }
}
