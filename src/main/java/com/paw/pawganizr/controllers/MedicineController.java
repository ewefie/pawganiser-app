package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Medicine;
import com.paw.pawganizr.services.MedicineService;
import com.paw.pawganizr.wrappers.Medicines;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://pawganiser.sdacademy.xyz"})
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/pets/{petId}/medicines/{medicineId}")
    @PreAuthorize("hasRole('USER')")
    public Medicine findById(@PathVariable(name = "petId") final UUID petId,
                             @PathVariable(name = "medicineId") final UUID medicineId) {
        return medicineService.findExistingMedicineById(medicineId);
    }

    @GetMapping("/pets/{petId}/medicines")
    @PreAuthorize("hasRole('USER')")
    public Medicines findAll(@PathVariable(name = "petId") final UUID petId) {
        return medicineService.findAllMedicinesByPetId(petId);
    }

    @PostMapping("/pets/{petId}/medicines")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Medicine create(@PathVariable(name = "petId") final UUID petId,
                           @RequestBody @Valid final Medicine medicine) {
        return medicineService.addMedicineToPet(petId, medicine);
    }

    @PutMapping("/pets/{petId}/medicines/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public Medicine update(@PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "medicineId") final UUID medicineId,
                           @RequestBody @Valid final Medicine medicine) {
        return medicineService.updateMedicine(medicineId, medicine);
    }


    @DeleteMapping("/pets/{petId}/medicines/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteById(@PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "medicineId") final UUID medicineId) {
        medicineService.deleteById(medicineId);
    }

    @DeleteMapping("/pets/{petId}/medicines")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteAll(@PathVariable(name = "petId") final UUID petId) {
        medicineService.deleteAllMedicines(petId);
    }
}
