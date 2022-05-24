package com.user.profile.worker.service.processors;

import static com.user.profile.model.mapper.BusinessProfileMapper.MAPPER_INSTANCE;
import static java.util.Collections.EMPTY_LIST;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.user.profile.core.exceptions.RetryRequiredException;
import com.user.profile.core.exceptions.UpdateFailureException;
import com.user.profile.core.repository.jpa.SubscriptionRepository;
import com.user.profile.core.repository.mongo.BusinessProfileUpdateRequestRepository;
import com.user.profile.model.commons.SubscriptionApplication;
import com.user.profile.model.commons.SubscriptionStatus;
import com.user.profile.model.domain.jpa.SubscriptionEntity;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.ClientValidationResponseDTO;
import com.user.profile.model.dto.ClientValidationResponseDTO.Status;
import com.user.profile.model.mapper.BusinessProfileMapper;
import com.user.profile.worker.config.SubscriberClientConfiguration;
import com.user.profile.worker.service.RestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Component
public class SubscriberValidationProcessor implements Processor {

    @Autowired
    private SubscriptionRepository repository;

    @Autowired
    private BusinessProfileUpdateRequestRepository updateRepository;

    @Autowired
    private RestService restService;

    @Autowired
    private SubscriberClientConfiguration clientConfiguration;

    @Override
    public void process(final Exchange exchange) throws Exception {
        BusinessProfileUpdateRequestDoc doc = exchange.getIn().getBody(BusinessProfileUpdateRequestDoc.class);
        Set<SubscriptionEntity> activeSubscriptions =
                repository.findByBusinessProfileIdAndSubscriptionStatus(
                        doc.getBusinessProfileId(), SubscriptionStatus.ACTIVE);
        log.info("{} number of active subscriptions found for profile id : {}", activeSubscriptions.size(),
                doc.getBusinessProfileId());
        final Map<Status, List<ResponseForUpdate>> data =
                activeSubscriptions.parallelStream()
                .map(sub -> {
                    try {
                        ClientValidationResponseDTO response =
                                restService.validateUpdateRequestFromClient(clientConfiguration.getApplications()
                                        .get(sub.getSubscriptionServiceName()).getUrl(),
                                        sub.getSubscriptionServiceName(), doc);
                        return ResponseForUpdate.of(sub.getSubscriptionServiceName(), doc.getId().toString(), response);
                    } catch (URISyntaxException e) {
                        log.error("URI syntax exception was found. Pushing for retry for {}",
                                sub.getSubscriptionServiceName().name());
                        //TODO : push event on kafka for retry
                        return ResponseForUpdate.of(sub.getSubscriptionServiceName(), doc.getId().toString(),
                                new ClientValidationResponseDTO(Status.RETRY_REQUIRED));
                    }
                }).collect(Collectors.groupingBy(responseForUpdate -> responseForUpdate.getResponse().getResponse()));

        if(data.getOrDefault(Status.DENIED, EMPTY_LIST).size() > 0) {
            doc.setStatus(com.user.profile.model.domain.mongo.Status.FAILED);
            updateRepository.save(doc);
            exchange.setException(new UpdateFailureException());
        } else if(data.getOrDefault(Status.RETRY_REQUIRED, EMPTY_LIST).size() > 0) {
            doc.setStatus(com.user.profile.model.domain.mongo.Status.RETRY_PENDING);
            updateRepository.save(doc);
            exchange.setException(new RetryRequiredException());
        } else {
            doc.setStatus(com.user.profile.model.domain.mongo.Status.DONE);
            exchange.getIn().setBody(doc);
        }
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class ResponseForUpdate {

        private SubscriptionApplication app;
        private String updateId;
        private ClientValidationResponseDTO response;

    }
}
