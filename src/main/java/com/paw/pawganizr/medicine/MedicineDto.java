package com.paw.pawganizr.medicine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class MedicineDto {
    @NotNull
    @Length(min = 2)
    @ApiModelProperty(example = "Xanax", required = true)
    private String name;
    @ApiModelProperty(example = "Once a day before a meal")
    private String dosage;
    @ApiModelProperty(example = "ESSENTIAL")
    private MedicineImportancy importancy;
}

