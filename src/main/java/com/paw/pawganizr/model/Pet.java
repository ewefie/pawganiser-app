package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pet {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    private Date createdAt;
    /**
     * some of details will be mandatory:
     */
    private String petName;

    //todo: probably will be changed to several classes extending pet class
    @Enumerated(value = EnumType.STRING)
    private PetType type;


    //todo: not sure if it correct
    //private Set<Long> ownersIds;
//    @ManyToMany(mappedBy = "pets")
//    private Set<AppUser> owners;
    @ManyToOne
    private AppUser owner;

    /**
     * ******************************* *
     * rest of details will be optional
     */
    private PetGender gender;

    private String chipNumber; //chip number

    //nullable
    private String petAvatarUrl;

    private CoatColor color;

//    private CoatPattern pattern;

//    private CoatLength coatLength;
//    private EyeColor eyeColor;

    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Medicine> medicines;

    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MedicalService> medicalServices;

    @ToString.Exclude
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Nutrition> nutrition;

    private LocalDate birthDate;
    /**
     * only after checking "pet is dead" option, input for death date should be shown
     */
    private boolean dead;
    private LocalDate deathDate;

    /**
     * *************************************************************************************************************** *
     * additional details only for pets having pedigree, inputs will be shown only after checking "have pedigree" option.
     */
    private Boolean pedigree;
    private String pedigreeNum;

    private String breeder;

    @Column(name = "race")
    private String breed;

    private String motherName;
    private String fatherName;
}
