package com.paw.pawganizr.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    @NotNull
    @Length(min = 2)
    private String name;
    private String dosage;
    private MedicineImportancy importancy;
}
