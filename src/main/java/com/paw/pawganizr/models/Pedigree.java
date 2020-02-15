package com.paw.pawganizr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Entity(name = "pedigrees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedigree {
    @JsonIgnore
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Length(min = 1)
    @Column(name = "pedigree_number")
    private String pedigreeNumber;

    @Column(name = "breeder")
    private String breeder;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "father_name")
    private String fatherName;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
