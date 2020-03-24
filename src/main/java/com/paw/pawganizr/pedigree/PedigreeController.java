package com.paw.pawganizr.pedigree;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Long;

@RestController
@RequestMapping("/users/me/pets")
public class PedigreeController {

    private final PedigreeService pedigreeService;

    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping("/{petId}/pedigree/")
    public Pedigree getPetsPedigree(@PathVariable(name = "petId") final Long petId) {
        return pedigreeService.findExistingPedigree(petId);
    }

    @DeleteMapping("/{petId}/pedigree/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetsPedigree(@PathVariable(name = "petId") final Long petId) {
        pedigreeService.deletePedigree(petId);
    }

    @PutMapping("/{petId}/pedigree/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePetsPedigree(@PathVariable(name = "petId") final Long petId, @Valid @RequestBody final Pedigree pedigree) {
        pedigreeService.saveOrUpdate(petId, pedigree);
    }
}
