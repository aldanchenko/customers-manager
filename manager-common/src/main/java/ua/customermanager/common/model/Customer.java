package ua.customermanager.common.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Customer entity class.
 */
@Entity
@Table(name = "customers")
public class Customer implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "last_name")
    private String lastName = "";

    @Column
    private String email = "";

    @Column(name = "phone_number")
    private String phoneNumber = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
