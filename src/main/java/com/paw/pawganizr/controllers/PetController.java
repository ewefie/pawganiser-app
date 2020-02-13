package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.security.CurrentUser;
import com.paw.pawganizr.security.UserPrincipal;
import com.paw.pawganizr.services.PetService;
import com.paw.pawganizr.wrappers.BasicPetInfos;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://pawganiser.sdacademy.xyz"})
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Pet createPet(@Valid @RequestBody final Pet pet, @CurrentUser final UserPrincipal principal) {
        return petService.addPetToUser(pet, principal.getId());
    }

    @GetMapping("/pets/{petId}")
    @PreAuthorize("hasRole('USER')")
    public Pet findPetById(@PathVariable("petId") final UUID petId, @CurrentUser final UserPrincipal principal) {
        return petService.findExistingPetById(petId);
    }

    @GetMapping("/pets")
    @PreAuthorize("hasRole('USER')")
    public BasicPetInfos findAllUsersPets(@CurrentUser final UserPrincipal principal) {
        return petService.getBasicPetInfoByUserId(principal.getId());
    }

    @DeleteMapping("/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deletePetById(@PathVariable("petId") final UUID petId, @CurrentUser final UserPrincipal principal) {
        petService.deletePetById(petId);
    }

    @DeleteMapping("/pets")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteAllPets(@CurrentUser final UserPrincipal principal) {
        petService.deleteAllPetsByUserId(principal.getId());
    }

    @PutMapping("/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void updatePet(@PathVariable("petId") final UUID petId, @RequestBody @Valid final Pet pet, @CurrentUser final UserPrincipal principal) {
        petService.updatePet(petId, pet);
    }
}
