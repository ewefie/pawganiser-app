package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pedigree;
import com.paw.pawganizr.services.PedigreeService;
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
    public Pedigree getPetsPedigree(@PathVariable(name = "petId") final UUID petId) {
        return pedigreeService.findPedigree(petId);
    }

    @DeleteMapping("/pets/{petId}/pedigree")
    public void deletePetsPedigree(@PathVariable(name = "petId") final UUID petId) {
        pedigreeService.deletePedigree(petId);
    }

    @PostMapping("/pets/{petId}/pedigree")
    public Pedigree addPetsPedigree(@PathVariable(name = "petId") final UUID petId, @RequestBody @Valid final Pedigree pedigree) {
        return pedigreeService.addPedigree(petId, pedigree);
    }

    @PutMapping("/pets/{petId}/pedigree")
    public Pedigree updatePetsPedigree(@PathVariable(name = "petId") final UUID petId, @RequestBody @Valid final Pedigree pedigree) {
        return pedigreeService.updatePedigree(petId, pedigree);
    }
}
