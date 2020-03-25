package com.paw.pawganizr.pedigree;

import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "pedigree_number")
    private String pedigreeNumber;

    @Column(name = "breeder")
    private String breeder;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "father_name")
    private String fatherName;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
