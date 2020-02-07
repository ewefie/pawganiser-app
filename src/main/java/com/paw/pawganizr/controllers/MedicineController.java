package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Medicine;
import com.paw.pawganizr.services.MedicineService;
import com.paw.pawganizr.wrappers.Medicines;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/{userId}/pets/{petId}/medicines/{medicineId}")
    public Medicine findById(@PathVariable(name = "userId") final UUID userId,
                             @PathVariable(name = "petId") final UUID petId,
                             @PathVariable(name = "medicineId") final UUID medicineId) {
        return medicineService.findExistingMedicineById(medicineId, petId, userId);
    }

    @GetMapping("/{userId}/pets/{petId}/medicines")
    public Medicines findAll(@PathVariable(name = "userId") final UUID userId,
                             @PathVariable(name = "petId") final UUID petId) {
        return medicineService.findAllMedicinesByPetId(petId, userId);
    }

    @PostMapping("/{userId}/pets/{petId}/medicines")
    @ResponseStatus(HttpStatus.CREATED)
    public Medicine create(@PathVariable(name = "userId") final UUID userId,
                           @PathVariable(name = "petId") final UUID petId,
                           @RequestBody @Valid final Medicine medicine) {
        return medicineService.addMedicineToPet(userId, petId, medicine);
    }

    @PutMapping("/{userId}/pets/{petId}/medicines/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Medicine update(@PathVariable(name = "userId") final UUID userId,
                           @PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "medicineId") final UUID medicineId,
                           @RequestBody @Valid final Medicine medicine) {
        return medicineService.updateMedicine(medicineId, medicine);
    }


    @DeleteMapping("/{userId}/pets/{petId}/medicines/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "userId") final UUID userId,
                           @PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "medicineId") final UUID medicineId) {
        medicineService.deleteById(medicineId);
    }

    @DeleteMapping("/{userId}/pets/{petId}/medicines")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable(name = "userId") final UUID userId,
                          @PathVariable(name = "petId") final UUID petId) {
        medicineService.deleteAllMedicines(petId);
    }
}
