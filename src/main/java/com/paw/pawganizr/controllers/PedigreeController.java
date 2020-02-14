package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pedigree;
import com.paw.pawganizr.services.PedigreeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController("/api/users/")
public class PedigreeController {

    private final PedigreeService pedigreeService;

    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping("/pets/{petId}/pedigree")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedigree getPetsPedigree(@PathVariable(name = "petId") final UUID petId) {
        return pedigreeService.findExistingPedigree(petId);
    }

    @DeleteMapping("/pets/{petId}/pedigree")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetsPedigree(@PathVariable(name = "petId") final UUID petId) {
        pedigreeService.deletePedigree(petId);
    }

    @PostMapping("/pets/{petId}/pedigree")
    public Pedigree addPetsPedigree(@PathVariable(name = "petId") final UUID petId, @RequestBody @Valid final Pedigree pedigree) {
        return pedigreeService.addPedigree(petId, pedigree);
    }

    @PutMapping("/pets/{petId}/pedigree")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePetsPedigree(@PathVariable(name = "petId") final UUID petId, @RequestBody @Valid final Pedigree pedigree) {
         pedigreeService.updatePedigree(petId, pedigree);
    }
}
