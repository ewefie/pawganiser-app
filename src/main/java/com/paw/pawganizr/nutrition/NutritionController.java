package com.paw.pawganizr.nutrition;

import com.paw.pawganizr.security.user.CurrentUser;
import com.paw.pawganizr.security.user.UserPrincipal;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/me/pets")
public class NutritionController {

    private final NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @PostMapping(value = "/{petId}/nutrients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a nutrition to one of your pets", response = NutritionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nutrition successfully uploaded"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public NutritionDto addNutrition(@Valid @RequestBody final NutritionDto nutritionDto,
                                     @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                     @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return nutritionService.saveNutrition(nutritionDto, petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/nutrients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a list of all nutrients assigned to one of your pets", response = Nutrients.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved pets"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Nutrients getAllNutrients(@ApiIgnore @CurrentUser final UserPrincipal principal,
                                     @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId) {
        return nutritionService.getAllNutrientsByPetId(petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/nutrients/{nutritionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a nutrition by its ID", response = NutritionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a nutrition"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public NutritionDto getNutritionById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                         @ApiParam(value = "Nutrition ID", required = true) @PathVariable(name = "nutritionId") final Long nutritionId,
                                         @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return nutritionService.getNutritionById(nutritionId, petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/nutrients/{nutritionId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a nutrition by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Nutrition successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteNutritionById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                    @ApiParam(value = "Nutrition ID", required = true) @PathVariable(name = "nutritionId") final Long nutritionId,
                                    @ApiIgnore @CurrentUser final UserPrincipal principal) {
        nutritionService.deleteNutritionById(nutritionId, petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/nutrients")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all of nutrients assigned to pet")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Nutrients successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteAllNutrients(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                   @ApiIgnore @CurrentUser final UserPrincipal principal) {
        nutritionService.deleteAllNutrientsByPetId(petId, principal.getId());
    }

    @PutMapping(value = "/{petId}/nutrients/{nutritionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update a nutrition")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nutrition successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public NutritionDto updateNutrition(@Valid @RequestBody final NutritionDto nutritionDto,
                                        @ApiParam(value = "Nutrition ID", required = true) @PathVariable(name = "nutritionId") final Long nutritionId,
                                        @ApiParam(value = "Nutrition ID", required = true) @PathVariable("petId") final Long petId,
                                        @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return nutritionService.updateNutrition(nutritionDto, nutritionId, petId, principal.getId());
    }
}
