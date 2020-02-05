package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/{userId}/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@Valid @RequestBody final Pet pet, @PathVariable("userId") final UUID userId) {
        return petService.addPetToUser(pet, userId);
    }



//    public Pets
}
