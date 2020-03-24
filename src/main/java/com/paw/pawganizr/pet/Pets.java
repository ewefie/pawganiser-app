package com.paw.pawganizr.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Pets {
    private List<PetDto> pets;
}
