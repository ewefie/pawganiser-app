package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Treatment;
import com.paw.pawganizr.services.TreatmentService;
import com.paw.pawganizr.wrappers.Treatments;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("/{userId}/pets/{petId}/treatments/{treatmentId}")
    public Treatment findById(@PathVariable(name = "userId") final UUID userId,
                              @PathVariable(name = "petId") final UUID petId,
                              @PathVariable(name = "treatmentId") final UUID treatmentId) {
        return treatmentService.findExistingTreatmentById(treatmentId, petId, userId);
    }

    @GetMapping("/{userId}/pets/{petId}/treatments")
    public Treatments findAll(@PathVariable(name = "userId") final UUID userId,
                              @PathVariable(name = "petId") final UUID petId) {
        return treatmentService.findAllTreatmentsByPetId(petId, userId);
    }

    @PostMapping("/{userId}/pets/{petId}/treatments")
    @ResponseStatus(HttpStatus.CREATED)
    public Treatment create(@PathVariable(name = "userId") final UUID userId,
                            @PathVariable(name = "petId") final UUID petId,
                            @RequestBody @Valid final Treatment treatment) {
        return treatmentService.addTreatmentToPet(userId, petId, treatment);
    }

    @PutMapping("/{userId}/pets/{petId}/treatments/{treatmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Treatment update(@PathVariable(name = "userId") final UUID userId,
                            @PathVariable(name = "petId") final UUID petId,
                            @PathVariable(name = "treatmentId") final UUID treatmentId,
                            @RequestBody @Valid final Treatment treatment) {
        return treatmentService.updateTreatment(treatmentId, treatment);
    }


    @DeleteMapping("/{userId}/pets/{petId}/treatments/{treatmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "userId") final UUID userId,
                           @PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "treatmentId") final UUID treatmentId) {
        treatmentService.deleteById(treatmentId);
    }

    @DeleteMapping("/{userId}/pets/{petId}/treatments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable(name = "userId") final UUID userId,
                          @PathVariable(name = "petId") final UUID petId) {
        treatmentService.deleteAllTreatments(petId);
    }
}
