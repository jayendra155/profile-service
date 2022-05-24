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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.user.profile.model.commons.AddressType;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@Data
@Entity
@Table(name = "address", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "business_profile_id", "address_type" }) })
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_profile_id")
    @JsonBackReference("businessAddresses")
    private BusinessProfileEntity businessProfile;

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) && addressType == that.addressType && Objects.equals(
                businessProfile, that.businessProfile) && Objects.equals(line1, that.line1)
                && Objects.equals(line2, that.line2) && Objects.equals(city, that.city)
                && Objects.equals(state, that.state) && Objects.equals(country, that.country)
                && Objects.equals(zipcode, that.zipcode);
    }

    @Override public int hashCode() {
        return Objects.hash(id, addressType, businessProfile, line1, line2, city, state, country, zipcode);
    }
}
