package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Pedigree;
import com.paw.pawganizr.services.PedigreeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/pets")
public class PedigreeController {

    private final PedigreeService pedigreeService;

    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping("/{petId}/pedigree/")
    public Pedigree getPetsPedigree(@PathVariable(name = "petId") final UUID petId) {
        return pedigreeService.findExistingPedigree(petId);
    }

    @DeleteMapping("/{petId}/pedigree/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetsPedigree(@PathVariable(name = "petId") final UUID petId) {
        pedigreeService.deletePedigree(petId);
    }

    @PutMapping("/{petId}/pedigree/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePetsPedigree(@PathVariable(name = "petId") final UUID petId, @Valid @RequestBody final Pedigree pedigree) {
        pedigreeService.saveOrUpdate(petId, pedigree);
    }
}
