package com.paw.pawganizr.wrappers;

import com.paw.pawganizr.models.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Pets {
    private List<Pet> pets;
}
