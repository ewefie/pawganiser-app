package com.paw.pawganizr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "app_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    /**
     * mandatory for creating new account:
     */
    @NotNull
    @Length(min = 2, message = "First name has to be at at least 2 character long")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Length(min = 2, message = "Last name has to be at at least 2 character long")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email", updatable = false)
    @Email(message = "Invalid email")
    private String email;

    /**
     * optional fields
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Contact> contacts;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Pet> pets;

    //sprawdzić czy istnieje o takim mailu
}
