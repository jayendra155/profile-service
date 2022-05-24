package com.user.profile.api.controller;

import static com.user.profile.model.mapper.BusinessProfileMapper.MAPPER_INSTANCE;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.profile.api.controller.spec.BusinessProfileSpec;
import com.user.profile.api.orchestration.BusinessProfileUpdateOrchestrator;
import com.user.profile.api.service.BusinessProfileService;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;
import com.user.profile.model.dto.BusinessProfileDTO;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;
import com.user.profile.model.mapper.BusinessProfileMapper;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 19/04/22
 */
@Slf4j
@RestController
@RequestMapping("/business-profile")
public class BusinessProfileController implements BusinessProfileSpec {

    @Autowired
    private BusinessProfileService businessProfileService;

    @Autowired
    private BusinessProfileUpdateOrchestrator orchestrator;

    @GetMapping(path = "/{id}", produces = HAL_JSON_VALUE)
    public ResponseEntity<BusinessProfileDTO> getBusinessProfile(@PathVariable Long id) {
        log.debug("Recieved request to fetch profile with id : {}", id);
        BusinessProfileDTO dto = MAPPER_INSTANCE.fromEntity(businessProfileService.getById(id));
        dto.add(linkTo(BusinessProfileController.class).slash(id).withSelfRel());
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping
    @RateLimiter(name = "profileUpdate", fallbackMethod = "getMessageFallBack")
    public ResponseEntity<BusinessProfileUpdateResponseDTO> updateOrCreateProfile(
            @Valid @RequestBody BusinessProfileUpdateRequestDTO request) {
        BusinessProfileUpdateResponseDTO response = null;
        if (Objects.isNull(request.getId())) {
            log.info("Id was null. Creating new profile");
            BusinessProfileEntity newBusinessProfile = businessProfileService.createNewBusinessProfile(
                    MAPPER_INSTANCE.fromRequestDTOtoEntity(request));
            log.info("New profile id : {}", newBusinessProfile.getId());
            response = MAPPER_INSTANCE.fromEntitytoResponseDTO(newBusinessProfile);
        } else {
            response = orchestrator.execute(request);
        }
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<String> getMessageFallBack(RequestNotPermitted exception) {

        log.info("Rate limit has applied, So no further calls are getting accepted");

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Too many requests : No further request will be accepted. Please try after sometime");
    }

    @Override
    @GetMapping(path = "/{profileId}/pending-updates")
    public ResponseEntity<Page<BusinessProfileUpdateResponseDTO>> fetchAllPendingUpdatesForProfile(
            @PathVariable Long profileId, @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "createdAt") String property,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, property));
        return ResponseEntity.ok(businessProfileService.fetchAllPendingUpdatesForProfile(profileId, pageRequest));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteProfile(@PathVariable Long profileId) {
        businessProfileService.deleteProfile(profileId);
        return ResponseEntity.accepted().build();
    }

    @Override
    @GetMapping(path = "/update-status/{id}")
    public ResponseEntity<BusinessProfileUpdateResponseDTO> getUpdateByUpdateId(@PathVariable String id) {
        return ResponseEntity.ok(businessProfileService.fetchPendingUpdateById(id));
    }

}
