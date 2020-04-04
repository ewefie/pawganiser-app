package com.paw.pawganizr.nutrition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class NutritionDto {
    @Length(min = 2)
    @ApiModelProperty(example = "Adult cat grain free tuna flavour", required = true)
    private String foodName;
    @ApiModelProperty(example = "Whiskers")
    private String brand;
    @ApiModelProperty(example = "5/6 of a cup per day")
    private String description;
}
