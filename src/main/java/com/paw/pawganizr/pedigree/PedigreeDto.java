package com.paw.pawganizr.pedigree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PedigreeDto {
    @ApiModelProperty(example = "1234567890987654", required = true)
    private String pedigreeNumber;
    @ApiModelProperty(example = "DesertStar*GB")
    private String breeder;
    @ApiModelProperty(example = "Daisy")
    private String motherName;
    @ApiModelProperty(example = "Walter")
    private String fatherName;
}
