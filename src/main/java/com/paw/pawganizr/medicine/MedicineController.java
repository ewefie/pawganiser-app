package com.paw.pawganizr.medicine;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Long;

@RestController
@RequestMapping("/users/me")
@CrossOrigin(origins = {"http://localhost:4200", "http://pawganiser.sdacademy.xyz"})
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/pets/{petId}/medicines/{medicineId}")
    @PreAuthorize("hasRole('USER')")
    public Medicine findById(@PathVariable(name = "petId") final Long petId,
                             @PathVariable(name = "medicineId") final Long medicineId) {
        return medicineService.findExistingMedicineById(medicineId);
    }

    @GetMapping("/pets/{petId}/medicines")
    @PreAuthorize("hasRole('USER')")
    public Medicines findAll(@PathVariable(name = "petId") final Long petId) {
        return medicineService.findAllMedicinesByPetId(petId);
    }

    @PostMapping("/pets/{petId}/medicines")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Medicine create(@PathVariable(name = "petId") final Long petId,
                           @RequestBody @Valid final Medicine medicine) {
        return medicineService.addMedicineToPet(petId, medicine);
    }

    @PutMapping("/pets/{petId}/medicines/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public Medicine update(@PathVariable(name = "petId") final Long petId,
                           @PathVariable(name = "medicineId") final Long medicineId,
                           @RequestBody @Valid final Medicine medicine) {
        return medicineService.updateMedicine(medicineId, medicine);
    }


    @DeleteMapping("/pets/{petId}/medicines/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteById(@PathVariable(name = "petId") final Long petId,
                           @PathVariable(name = "medicineId") final Long medicineId) {
        medicineService.deleteById(medicineId);
    }

    @DeleteMapping("/pets/{petId}/medicines")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteAll(@PathVariable(name = "petId") final Long petId) {
        medicineService.deleteAllMedicines(petId);
    }
}
