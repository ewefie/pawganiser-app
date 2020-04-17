package com.paw.pawganizr.treatment;

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
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping(value = "/{petId}/treatments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a treatment to one of your pets", response = TreatmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Treatment successfully uploaded"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public TreatmentDto addTreatment(@Valid @RequestBody final TreatmentDto treatmentDto,
                                     @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                     @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return treatmentService.saveTreatment(treatmentDto, petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/treatments", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a list of all treatments assigned to one of your pets", response = Treatments.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved pets"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Treatments getAllTreatments(@ApiIgnore @CurrentUser final UserPrincipal principal,
                                       @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId) {
        return treatmentService.getAllTreatmentsByPetId(petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/treatments/{treatmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a treatment by its ID", response = TreatmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a treatment"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public TreatmentDto getTreatmentById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                         @ApiParam(value = "Treatment ID", required = true) @PathVariable(name = "treatmentId") final Long treatmentId,
                                         @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return treatmentService.getTreatmentById(treatmentId, petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/treatments/{treatmentId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a treatment by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Treatment successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteTreatmentById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                    @ApiParam(value = "Treatment ID", required = true) @PathVariable(name = "treatmentId") final Long treatmentId,
                                    @ApiIgnore @CurrentUser final UserPrincipal principal) {
        treatmentService.deleteTreatmentById(treatmentId, petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/treatments")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all of treatments assigned to pet")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Treatments successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteAllTreatments(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                    @ApiIgnore @CurrentUser final UserPrincipal principal) {
        treatmentService.deleteAllTreatmentsByPetId(petId, principal.getId());
    }

    @PutMapping(value = "/{petId}/treatments/{treatmentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update a treatment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treatment successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public TreatmentDto updateTreatment(@Valid @RequestBody final TreatmentDto treatmentDto,
                                        @ApiParam(value = "Treatment ID", required = true) @PathVariable(name = "treatmentId") final Long treatmentId,
                                        @ApiParam(value = "Treatment ID", required = true) @PathVariable("petId") final Long petId,
                                        @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return treatmentService.updateTreatment(treatmentDto, treatmentId, petId, principal.getId());
    }
}
