package com.paw.pawganizr.pet;

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
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a pet", response = Pets.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet successfully uploaded"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public PetDto addPet(@Valid @RequestBody final PetDto pet,
                         @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return petService.savePet(pet, principal.getId());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get an object containing a list of all your pets", response = Pets.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved pets"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Pets getAllPets(@ApiIgnore @CurrentUser final UserPrincipal principal) {
        return petService.getAllPetsByUserId(principal.getId());
    }

    @GetMapping(value = "/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get one of your pets by its ID", response = PetDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a pet"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public PetDto getPetById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                             @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return petService.getPetById(petId, principal.getId());
    }


    @DeleteMapping(value = "/{petId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete your pet by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pet successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deletePetById(@ApiIgnore @CurrentUser final UserPrincipal userPrincipal,
                              @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId) {
        petService.deletePetById(petId, userPrincipal.getId());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all of your pets")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pets successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteAllPets(@ApiIgnore @CurrentUser final UserPrincipal userPrincipal) {
        petService.deleteAllPetsByUserId(userPrincipal.getId());
    }

    @PutMapping(value = "/{petId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update pets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pets successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public PetDto updatePet(@ApiParam(value = "Pet ID", required = true) @PathVariable("petId") final Long petId,
                            @RequestBody @Valid Pet updatedPet, @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return petService.updatePet(petId, updatedPet, principal.getId());
    }
}
