package com.paw.pawganizr.models;

import com.paw.pawganizr.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity(name = "contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "contact_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(name = "description")
    private String description;

    @Column(name = "contact_name")
    @NotNull
    @Length(min = 2)
    private String name;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private AppUser user;

    @Column(name = "phone_number")
    @Pattern(regexp = "[\\d]{9}")
    @NotNull
    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Invalid email")
    private String email;
}
