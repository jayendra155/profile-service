package com.user.profile.worker.service.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.user.profile.core.exceptions.MongoDocumentNotFoundException;
import com.user.profile.core.repository.mongo.BusinessProfileUpdateRequestRepository;
import com.user.profile.model.domain.mongo.Status;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Component
public class EventToRecordProcessor implements Processor {

    @Autowired
    private BusinessProfileUpdateRequestRepository requestRepository;

    @Override
    public void process(final Exchange exchange) throws Exception {
        String id = exchange.getMessage().getBody(String.class);
        log.info("Fetching data from database for id : {}", id);
        BusinessProfileUpdateRequestDoc doc = requestRepository.findById(id)
                .orElseThrow(() -> new MongoDocumentNotFoundException(String.format("%s record not found", id)));
        doc.setStatus(Status.IN_PROGRESS);
        requestRepository.save(doc);
        exchange.getIn().setBody(doc);
    }
}
