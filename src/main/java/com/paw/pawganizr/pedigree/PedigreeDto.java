package com.paw.pawganizr.pedigree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedigreeDto {
    @NotNull
    @Length(min = 1)
    private String pedigreeNumber;
    private String breeder;
    private String motherName;
    private String fatherName;
}
