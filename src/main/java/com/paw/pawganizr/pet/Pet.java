package com.paw.pawganizr.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.medicine.Medicine;
import com.paw.pawganizr.nutrition.Nutrition;
import com.paw.pawganizr.pedigree.Pedigree;
import com.paw.pawganizr.treatment.Treatment;
import com.paw.pawganizr.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Entity(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    /**
     * some of details will be mandatory:
     */
    @Column(name = "pet_name")
    @Length(min = 2)
    @NotNull
    private String petName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    /**
     * *******************************
     * rest of details will be optional
     */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private PetGender gender;

    @Column(name = "chip_number")
    private String chipNumber;

    @Column(name = "avatar_url")
    private String petAvatarUrl;

    @Column(name = "coat_color")
    private String color;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicine> medicines;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treatment> treatments;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nutrition> nutrition;

    @Column(name = "birth_name")
    private LocalDate birthDate;
    /**
     * only after checking "pet is dead" option, input for death date should be shown
     */
    @Column(name = "dead")
    private boolean dead;

    @Column(name = "death_date")
    private LocalDate deathDate;

    /**
     * *************************************************************************************************************** *
     * additional details only for pets having pedigree, inputs will be shown only after checking "have pedigree" option.
     */
    @Column(name = "race")
    private String race;

    @OneToOne
    @JoinColumn(name = "pedigree_id")
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private Pedigree pedigree;

    @AssertTrue
    @JsonIgnore
    private boolean isDeathDateValid() {
        return (!dead && isNull(deathDate)) || deathDate.isAfter(birthDate);
    }

    @AssertTrue
    @JsonIgnore
    private boolean isChipNumberValid() {
        return isNull(chipNumber) || chipNumber.length() == 15 || chipNumber.length() == 0;
    }
}
