package com.user.profile.core.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
public interface BusinessProfileRepository extends JpaRepository<BusinessProfileEntity, Long> {

}
