package com.paw.pawganizr.pedigree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Long;

@Entity(name = "pedigrees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedigree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

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
