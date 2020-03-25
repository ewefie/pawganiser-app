package com.paw.pawganizr.nutrition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionDto {
    @NotNull
    @Length(min = 2)
    private String foodName;
    private String brand;
    private String description;
}
