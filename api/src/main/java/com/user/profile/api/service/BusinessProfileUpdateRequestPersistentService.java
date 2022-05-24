package com.user.profile.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.profile.api.orchestration.interfaces.Action;
import com.user.profile.model.domain.mongo.Status;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Service
public class BusinessProfileUpdateRequestPersistentService
        implements Action<BusinessProfileUpdateRequestDoc, BusinessProfileUpdateRequestDoc> {

    @Autowired
    private BusinessProfileService service;

    @Autowired
    private Tracer tracer;

    @Override
    public BusinessProfileUpdateRequestDoc execute(BusinessProfileUpdateRequestDoc input) {
        input.setTraceId(tracer.currentSpan().context().traceIdString());
        input.setStatus(Status.RECEIVED);
        final BusinessProfileUpdateRequestDoc doc = service.insertNewUpdateRequest(input);
        log.info("Created record with id : {}", doc.getId().toString());
        return doc;
    }
}
