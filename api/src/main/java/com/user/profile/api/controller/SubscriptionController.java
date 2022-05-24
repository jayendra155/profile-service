package com.user.profile.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.profile.api.controller.spec.SubscriptionSpec;
import com.user.profile.model.commons.SubscriptionStatus;
import com.user.profile.model.dto.SubscriptionDTO;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Slf4j
@RestController
@RequestMapping("/subscription")
public class SubscriptionController implements SubscriptionSpec {

    @Override
    @GetMapping(path = "/profile/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SubscriptionDTO>> getAllSubscriptionsForProfile(@PathVariable Long profileId,
            @RequestParam(defaultValue = "ACTIVE") SubscriptionStatus status) {
        return ResponseEntity.ok(null);
    }

}
