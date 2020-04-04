package com.paw.pawganizr.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.contact.Contact;
import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "app_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    /**
     * mandatory for creating new account:
     */
    @NotNull
    @Length(min = 2, message = "Name has to be at at least 2 character long")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email", updatable = false)
    @Email(message = "Invalid email")
    private String email;

    /**
     * optional fields
     */
//    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

//    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

//    @JsonIgnore
    @Column(name = "password")
    private String password;
}
