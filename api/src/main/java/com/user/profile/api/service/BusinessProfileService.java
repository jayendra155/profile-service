package com.user.profile.api.service;

import static com.user.profile.model.mapper.BusinessProfileMapper.MAPPER_INSTANCE;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.user.profile.core.exceptions.MongoDocumentNotFoundException;
import com.user.profile.core.repository.jpa.BusinessProfileRepository;
import com.user.profile.core.repository.mongo.BusinessProfileUpdateRequestRepository;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;
import com.user.profile.model.domain.mongo.Status;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Slf4j
@Service
@CacheConfig(keyGenerator = "businessProfileKeyGenerator")
public class BusinessProfileService {

    @Autowired
    private BusinessProfileRepository repository;

    @Autowired
    private BusinessProfileUpdateRequestRepository requestUpdateRepository;

    @Cacheable(value = "businessProfileCache")
    public BusinessProfileEntity getById(Long id) {
        log.info("Fetching from database for id : {}", id);
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<BusinessProfileUpdateResponseDTO> fetchAllPendingUpdatesForProfile(Long id, Pageable pageable) {
        Set<Status> statusSet = new HashSet<>(
                Arrays.asList(Status.RETRY_PENDING, Status.IN_PROGRESS, Status.FAILED, Status.RECEIVED));
        log.info("Fetching pending updates for profile: {}, page: {}", id, pageable.getPageNumber());
        final Page<BusinessProfileUpdateRequestDoc> result =
                this.requestUpdateRepository.findByBusinessProfileIdAndStatusIn(
                        id, statusSet, pageable);
        return new PageImpl<>(result.stream().map(MAPPER_INSTANCE::fromUpdateDocument).collect(Collectors.toList()),
                pageable, pageable.getPageNumber());
    }

    //    @CachePut(keyGenerator = "businessProfileObjectKeyGenerator")
    public BusinessProfileUpdateRequestDoc insertNewUpdateRequest(BusinessProfileUpdateRequestDoc doc) {
        log.info("Inserting new Update request for : {}", doc.getBusinessProfileId());
        return requestUpdateRepository.insert(doc);
    }

    public BusinessProfileEntity createNewBusinessProfile(BusinessProfileEntity entity) {
        return repository.save(entity);
    }

    public BusinessProfileUpdateResponseDTO fetchPendingUpdateById(String id) {
        BusinessProfileUpdateRequestDoc requestDoc = requestUpdateRepository.findById(id)
                .orElseThrow(() -> new MongoDocumentNotFoundException(id));
        return MAPPER_INSTANCE.fromUpdateDocument(requestDoc);
    }

    public void deleteProfile(Long id) {

        //TODO: implement basic validation before proceeding with delete. Async approach can also be taken here.
        repository.deleteById(id);
        log.info("Deletion successful");
    }

}
