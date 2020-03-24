package com.paw.pawganizr.nutrition;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Long;

@RestController
@RequestMapping("/users/me")
@CrossOrigin(origins = {"http://localhost:4200", "http://pawganiser.sdacademy.xyz"})
public class NutritionController {

    private final NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }


    @GetMapping("/pets/{petId}/nutrients/{nutritionId}")
    public Nutrition findById(@PathVariable(name = "petId") final Long petId,
                              @PathVariable(name = "nutritionId") final Long nutritionId) {
        return nutritionService.findExistingNutritionById(nutritionId, petId);
    }

    @GetMapping("/pets/{petId}/nutrients")
    public Nutrients findAll(@PathVariable(name = "petId") final Long petId) {
        return nutritionService.findAllNutrientsByPetId(petId);
    }

    @PostMapping("/pets/{petId}/nutrients")
    @ResponseStatus(HttpStatus.CREATED)
    public Nutrition create(@PathVariable(name = "petId") final Long petId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutritionService.addNutritionToPet(petId, nutrition);
    }

    @PutMapping("/pets/{petId}/nutrients/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Nutrition update(@PathVariable(name = "petId") final Long petId,
                            @PathVariable(name = "nutritionId") final Long nutritionId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutritionService.updateNutrition(petId, nutritionId, nutrition);
    }


    @DeleteMapping("/pets/{petId}/nutrients/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "petId") final Long petId,
                           @PathVariable(name = "nutritionId") final Long nutritionId) {
        nutritionService.deleteById(nutritionId);
    }

    @DeleteMapping("/pets/{petId}/nutrients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable(name = "petId") final Long petId) {
        nutritionService.deleteAllNutrients(petId);
    }
}
