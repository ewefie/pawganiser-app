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

    @GetMapping("/{userId}/pets/{petId}")
    public Pet findPetById(@PathVariable("petId") final UUID petId, @PathVariable("userId") final UUID userId) {
        return petService.findExistingPetById(petId);
    }

    @GetMapping("/{userId}/pets")
    public BasicPetInfos findAllUserPets(@PathVariable("userId") final UUID userId) {
        return petService.getBasicPetInfoByUserId(userId);
    }

    @DeleteMapping("/{userId}/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable("petId") final UUID petId, @PathVariable("userId") final UUID userId) {
        petService.deletePetById(petId);
    }

    @DeleteMapping("/{userId}/pets")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPets(@PathVariable("userId") final UUID userId) {
        petService.deleteAllPetsByUserId(userId);
    }

    @PutMapping("/{userId}/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePet(@PathVariable("petId") final UUID petId, @PathVariable("userId") final UUID userId,
                          @RequestBody @Valid final Pet pet) {
        petService.updatePet(userId, petId, pet);
    }
}
