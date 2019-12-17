package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identyfikator jest generowany przez bazę
    private Long id;

    private String name;

    private LocalDate birthDate;
    private LocalDate deathDate;

    private CoatColor color;
    private CoatPattern pattern;
    private CoatLength length;
    private EyeColor eyeColor;
    private Sex sex;

    //fixme: find better words ;)
    private boolean alive;

    //todo: will be changed to several classes extending pet class
    @Enumerated(value = EnumType.STRING)
    private PetType type;

    //todo: change to enum
    private String breed;

    //fixme: what's the general name of place where people breed pets?
    private String cattery;


    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MedicalService> medicalServices;


    //Not sure if adding family as pets is a proper way...
    @ToString.Exclude
    @ManyToOne
    private Pet mother;

    @ToString.Exclude
    @ManyToOne
    private Pet father;

    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Pet> children;

    @ToString.Exclude
    @ManyToOne
    private AppUser owner;

    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Medicine> medicines;

    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Food> food;
}
