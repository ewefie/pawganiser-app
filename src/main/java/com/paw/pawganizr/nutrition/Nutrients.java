package com.paw.pawganizr.nutrition;

import com.paw.pawganizr.nutrition.Nutrition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Nutrients {
    private List<Nutrition> nutrients;
}
