package com.user.profile.model.domain.jpa;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@Data
@Entity
@Table(indexes = @Index(columnList = "userId"))
public class BusinessProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "businessProfile", fetch = FetchType.EAGER)
    @JsonManagedReference("businessAddresses")
    private Set<AddressEntity> addresses;

    @OneToMany(mappedBy = "businessProfile", fetch = FetchType.EAGER)
    @JsonManagedReference("subscriptions")
    private Set<SubscriptionEntity> subscriptions;

    private String companyName;

    private String legalName;

    private String email;

    private String website;

    private String taxIdentifierDocName;

    private String taxIdentifierDocId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Set<AddressEntity> addAddress(AddressEntity address) {
        if (Objects.isNull(addresses)) {
            addresses = new HashSet<>();
        }
        addresses.add(address);
        address.setBusinessProfile(this);
        return Collections.unmodifiableSet(addresses);
    }

    public Set<SubscriptionEntity> addSubscription(SubscriptionEntity subscription) {
        if (Objects.isNull(subscriptions)) {
            subscriptions = new HashSet<>();
        }
        subscriptions.add(subscription);
        subscription.setBusinessProfile(this);
        return Collections.unmodifiableSet(subscriptions);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BusinessProfileEntity that = (BusinessProfileEntity) o;
        return Objects.equals(id, that.id)
                //&& Objects.equals(profileOwner, that.profileOwner)
                && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt,
                that.updatedAt);
    }

    @Override public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt);
    }
}
