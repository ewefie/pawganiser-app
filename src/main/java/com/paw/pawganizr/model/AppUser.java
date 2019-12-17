package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identyfikator jest generowany przez bazę
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    //todo: two ways: vet can be a new class with some fields like name, address,
    // phone number etc, or it can be a some kind of object from google maps API.
    private String favouriteVet;
    private OwnerStatus status;

    //fixme: change to manyToMany - pets can have more than one owner
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Pet> pets;

    @CreatedDate
    private Date createdAt;


}
