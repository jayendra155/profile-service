package com.user.profile.worker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.user.profile.core.repository.jpa.BusinessProfileRepository;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Service
public class BusinessProfileService {

    @Autowired
    private BusinessProfileRepository repository;

    @CachePut(value = "businessProfileCache", keyGenerator = "#entity.getId()")
    public BusinessProfileEntity updateEntity(BusinessProfileEntity entity) {
        return repository.save(entity);
    }
}
