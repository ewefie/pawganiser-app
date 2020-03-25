package com.paw.pawganizr.nutrition;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Nutrients {
    private List<NutritionDto> nutrients;
}
