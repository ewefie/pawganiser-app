package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.services.PetService;
import com.paw.pawganizr.wrappers.BasicPetInfos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@Valid @RequestBody final Pet pet, final Principal principal) {
        return petService.addPetToUser(pet, principal);
    }

    @GetMapping("/pets/{petId}")
    public Pet findPetById(@PathVariable("petId") final UUID petId) {
        return petService.findExistingPetById(petId);
    }

    @GetMapping("/pets")
    public BasicPetInfos findAllUsersPets(final Principal principal) {
        return petService.getBasicPetInfoByPrincipal(principal);
    }


    @DeleteMapping("/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable("petId") final UUID petId) {
        petService.deletePetById(petId);
    }

    @DeleteMapping("/pets")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPets(final Principal principal) {
        petService.deleteAllPetsByPrincipal(principal);
    }

    @PutMapping("/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePet(@PathVariable("petId") final UUID petId, @RequestBody @Valid final Pet pet) {
        petService.updatePet(petId, pet);
    }
}
