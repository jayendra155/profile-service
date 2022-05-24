package com.user.profile.model.domain.jpa;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
@Data
@Embeddable
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(middleName, name.middleName)
                && Objects.equals(lastName, name.lastName);
    }

    @Override public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
