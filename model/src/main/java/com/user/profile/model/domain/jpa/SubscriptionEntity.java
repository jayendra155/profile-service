package com.user.profile.model.domain.jpa;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.user.profile.model.commons.SubscriptionApplication;
import com.user.profile.model.commons.SubscriptionStatus;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@Data
@Entity
@Table(name = "subscription", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "business_profile_id", "subscription_name" })
}, indexes = @Index(columnList = "business_profile_id"))
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_profile_id")
    @JsonBackReference("businessAddresses")
    private BusinessProfileEntity businessProfile;

    @Column(name = "subscription_name")
    private SubscriptionApplication subscriptionServiceName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SubscriptionStatus subscriptionStatus;

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SubscriptionEntity that = (SubscriptionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(businessProfile, that.businessProfile)
                && subscriptionServiceName == that.subscriptionServiceName
                && subscriptionStatus == that.subscriptionStatus;
    }

    @Override public int hashCode() {
        return Objects.hash(id, businessProfile, subscriptionServiceName, subscriptionStatus);
    }
}
