package com.paw.pawganizr.wrappers;

import com.paw.pawganizr.models.Nutrition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Nutrients {
    private List<Nutrition> nutrients;
}
