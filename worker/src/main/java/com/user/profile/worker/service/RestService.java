package com.user.profile.worker.service;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.user.profile.model.commons.SubscriptionApplication;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.dto.ClientValidationResponseDTO;
import com.user.profile.worker.util.WebUtils;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 02/05/22
 */
@Slf4j
@Service
public class RestService {

    @Autowired
    private WebClient webClient;

    @Retry(name = "#root.methodName+#root.args[1].name()", fallbackMethod = "fallBackForAPICall")
    public ClientValidationResponseDTO validateUpdateRequestFromClient(String clientURL,
     SubscriptionApplication application, BusinessProfileUpdateRequestDoc doc)
            throws URISyntaxException {

        ClientValidationResponseDTO response = this.webClient.post().uri(new URI(clientURL))
                .header("content-type", MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromProducer(Mono.just(doc), BusinessProfileUpdateRequestDoc.class))
                .retrieve()
                .onStatus(WebUtils.ANY_OTHER_THAN_2XX,
                        res -> res.bodyToMono(String.class)
                                .flatMap(error -> {
                                    log.error("Failure. Response code : {}", res.statusCode());
                                    //based on response code throw custom exception
                                    return Mono.error(() -> new RuntimeException("Failure in validation"));
                                }))
                .bodyToMono(ClientValidationResponseDTO.class).block();
        return response;
    }

    public ClientValidationResponseDTO fallBackForAPICall(Exception e) {
        //TODO: implement further retry using async scheduler
        return new ClientValidationResponseDTO(ClientValidationResponseDTO.Status.RETRY_REQUIRED);
    }
}
