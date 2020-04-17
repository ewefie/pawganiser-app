package com.paw.pawganizr.pedigree;

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
public class PedigreeController {

    private final PedigreeService pedigreeService;

    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }


    @PostMapping(value = "/{petId}/pedigree", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a pedigree to one of your pets", response = PedigreeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedigree successfully uploaded"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public PedigreeDto addPedigree(@Valid @RequestBody final PedigreeDto pedigreeDto,
                                   @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                   @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return pedigreeService.savePedigree(pedigreeDto, petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/pedigree", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get pets pedigree", response = PedigreeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a pedigree"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public PedigreeDto getPedigree(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                   @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return pedigreeService.getPedigree(petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/pedigree")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete pedigree assigned pet")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedigrees successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deletePedigree(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                               @ApiIgnore @CurrentUser final UserPrincipal principal) {
        pedigreeService.deletePedigree(petId, principal.getId());
    }

    @PutMapping(value = "/{petId}/pedigree", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update a pedigree")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedigree successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public PedigreeDto updatePedigree(@Valid @RequestBody final PedigreeDto pedigreeDto,
                                      @ApiParam(value = "Pedigree ID", required = true) @PathVariable("petId") final Long petId,
                                      @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return pedigreeService.updatePedigree(pedigreeDto, petId, principal.getId());
    }
}
