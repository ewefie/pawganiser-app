package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.services.PetService;
import com.paw.pawganizr.wrappers.BasicPetInfos;
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

    //czy tu powinno byc tylko id peta czy tez usera?
    @GetMapping("/{userId}/pets/{petId}")
    public Pet findPetById(@PathVariable("petId") final UUID petId, @PathVariable("userId") final UUID userId) {
        //fixme: check czy user istnieje/ ma dostęp?
        return petService.findExistingPetById(petId);
    }

    @GetMapping("/{userId}/pets/")
    public BasicPetInfos findAllUserPets(@PathVariable("userId") final UUID userId) {
        return petService.getBasicPetInfoByUserId(userId);
    }

    @DeleteMapping("/{userId}/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable("petId") final UUID petId, @PathVariable("userId") final UUID userId) {
        //fixme: check czy user istnieje/ ma dostęp?
        petService.delete(petId);
    }

    @PutMapping("/{userId}/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePet(@PathVariable("petId") final UUID petId, @PathVariable("userId") final UUID userId,
                          @RequestBody final Pet pet) {
        //fixme: check czy user istnieje/ ma dostęp?
        petService.updatePet(petId, pet);
    }


}
