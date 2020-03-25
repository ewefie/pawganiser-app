package com.paw.pawganizr.medicine;

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
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping(value = "/{petId}/medicines", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a medicine to one of your pets", response = MedicineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Medicine successfully uploaded"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public MedicineDto addMedicine(@Valid @RequestBody final MedicineDto medicineDto,
                                   @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                   @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return medicineService.saveMedicine(medicineDto, petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/medicines", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a list of all medicines assigned to one of your pets", response = Medicines.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved pets"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Medicines getAllMedicines(@ApiIgnore @CurrentUser final UserPrincipal principal,
                                     @ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId) {
        return medicineService.getAllMedicinesByPetId(petId, principal.getId());
    }

    @GetMapping(value = "/{petId}/medicines/{medicineId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a medicine by its ID", response = MedicineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a medicine"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public MedicineDto getMedicineById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                       @ApiParam(value = "Medicine ID", required = true) @PathVariable(name = "medicineId") final Long medicineId,
                                       @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return medicineService.getMedicineById(medicineId, petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/medicines/{medicineId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a medicine by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicine successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteMedicineById(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                   @ApiParam(value = "Medicine ID", required = true) @PathVariable(name = "medicineId") final Long medicineId,
                                   @ApiIgnore @CurrentUser final UserPrincipal principal) {
        medicineService.deleteMedicineById(medicineId, petId, principal.getId());
    }

    @DeleteMapping(value = "/{petId}/medicines")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all of medicines assigned to pet")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicines successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteAllMedicines(@ApiParam(value = "Pet ID", required = true) @PathVariable(name = "petId") final Long petId,
                                   @ApiIgnore @CurrentUser final UserPrincipal principal) {
        medicineService.deleteAllMedicinesByPetId(petId, principal.getId());
    }

    @PutMapping(value = "/{petId}/medicines/{medicineId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update a medicine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medicine successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public MedicineDto updateMedicine(@Valid @RequestBody final MedicineDto medicineDto,
                                      @ApiParam(value = "Medicine ID", required = true) @PathVariable(name = "medicineId") final Long medicineId,
                                      @ApiParam(value = "Medicine ID", required = true) @PathVariable("petId") final Long petId,
                                      @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return medicineService.updateMedicine(medicineDto, medicineId, petId, principal.getId());
    }
}
