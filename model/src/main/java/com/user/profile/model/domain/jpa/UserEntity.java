package com.user.profile.model.domain.jpa;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.user.profile.model.commons.UserType;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@Data
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String username;

    private String password;

    private UserType userType;

    @OneToOne(mappedBy = "profileOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("businessProfile")
    private BusinessProfileEntity businessProfile;

    @Embedded
    private Name name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
