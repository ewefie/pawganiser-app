package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    private Date createdAt;

    /**
     * mandatory for creating new account:
     */
    private String firstName;
    private String lastName;
    private String email;

    /**
     * optional
     */
    //todo: two ways: vet can be a new class with some fields like name, address,
    // phone number etc, or it can be a some kind of object from google maps API.
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Contact> contacts;
//    private OwnerStatus status;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Pet> pets;
}
