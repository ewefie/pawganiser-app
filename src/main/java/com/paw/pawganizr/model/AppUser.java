package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "app_users")
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
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Contact> contacts;


    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Pet> pets;
}
