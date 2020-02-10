package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Nutrition;
import com.paw.pawganizr.services.NutritionService;
import com.paw.pawganizr.wrappers.Nutrients;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:9000", "http://pawganiser.sdacademy.xyz"})
public class NutritionController {

    private final NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }


    @GetMapping("/pets/{petId}/nutrients/{nutritionId}")
    public Nutrition findById(@PathVariable(name = "petId") final UUID petId,
                              @PathVariable(name = "nutritionId") final UUID nutritionId) {
        return nutritionService.findExistingNutritionById(nutritionId, petId);
    }

    @GetMapping("/pets/{petId}/nutrients")
    public Nutrients findAll(@PathVariable(name = "petId") final UUID petId) {
        return nutritionService.findAllNutrientsByPetId(petId);
    }

    @PostMapping("/pets/{petId}/nutrients")
    @ResponseStatus(HttpStatus.CREATED)
    public Nutrition create(@PathVariable(name = "petId") final UUID petId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutritionService.addNutritionToPet(petId, nutrition);
    }

    @PutMapping("/pets/{petId}/nutrients/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Nutrition update(@PathVariable(name = "petId") final UUID petId,
                            @PathVariable(name = "nutritionId") final UUID nutritionId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutritionService.updateNutrition(petId, nutritionId, nutrition);
    }


    @DeleteMapping("/pets/{petId}/nutrients/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "nutritionId") final UUID nutritionId) {
        nutritionService.deleteById(nutritionId);
    }

    @DeleteMapping("/pets/{petId}/nutrients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable(name = "petId") final UUID petId) {
        nutritionService.deleteAllNutrients(petId);
    }
}
