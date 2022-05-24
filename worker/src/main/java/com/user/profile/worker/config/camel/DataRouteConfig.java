package com.user.profile.worker.config.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.user.profile.core.exceptions.MongoDocumentNotFoundException;
import com.user.profile.worker.AppConstants;
import com.user.profile.worker.service.processors.BusinessProfileUpdateRequestPersistentProcessor;
import com.user.profile.worker.service.processors.EventToRecordProcessor;
import com.user.profile.worker.service.processors.SubscriberValidationProcessor;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Configuration
public class DataRouteConfig extends RouteBuilder {

    @Autowired
    private EventToRecordProcessor eventToRecordProcessor;

    @Autowired
    private SubscriberValidationProcessor subscriberValidationProcessor;

    @Autowired
    private BusinessProfileUpdateRequestPersistentProcessor persistentProcessor;

    @Override
    public void configure() throws Exception {

//        onException(MongoDocumentNotFoundException.class)
//                .log(LoggingLevel.INFO, log, "Exception while fetching data from mongo");

        from(AppConstants.UPDATE_ROUTE)
                .autoStartup(true)
                .log("Recieved request for business profile update")
                .doTry()
                    .process(eventToRecordProcessor)
                    .process(subscriberValidationProcessor)
                    .process(persistentProcessor)
                .doCatch(MongoDocumentNotFoundException.class)
                    .process(exchange -> {})
                .end();

    }
}
