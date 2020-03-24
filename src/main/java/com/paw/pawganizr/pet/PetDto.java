package com.paw.pawganizr.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private Long id;
    @Length(min = 2)
    @NotNull
    private String petName;
    @NotNull
    private PetType type;
    private PetGender gender;
    private String chipNumber;
    private String petAvatarUrl;
    private String color;
    private LocalDate birthDate;
    private boolean dead;
    private LocalDate deathDate;
    private String race;

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
