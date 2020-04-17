package com.paw.pawganizr.nutrition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Nutrients {
    private List<NutritionDto> nutrients;
}
