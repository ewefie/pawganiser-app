package com.paw.pawganizr.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    private Date createdAt;

    /**
     * mandatory for creating new account:
     */
    @NotNull
    @Length(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Length(min = 2)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    @Email(message = "Invalid email")
    private String email;

    /**
     * optional fields
     */
    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;


    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}
