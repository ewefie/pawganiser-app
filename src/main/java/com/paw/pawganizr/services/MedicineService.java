package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.Medicine;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.MedicineRepository;
import com.paw.pawganizr.wrappers.Medicines;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MedicineService {

    private final PetService petService;
    private final MedicineRepository medicineRepository;
    private final UserService userService;


    public MedicineService(PetService petService, MedicineRepository medicineRepository, UserService userService) {
        this.petService = petService;
        this.medicineRepository = medicineRepository;
        this.userService = userService;
    }

    public Medicine addMedicineToPet(final UUID userId, final UUID petId, final Medicine medicineToAdd) {
        userService.findExistingUser(userId);
        final Pet existingPet = petService.findExistingPetById(petId);
        medicineToAdd.setPet(existingPet);
        return medicineRepository.save(medicineToAdd);
    }

    public void deleteById(final UUID medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    public void deleteAllMedicines(final UUID petId) {
        petService.findExistingPetById(petId);
        medicineRepository.deleteAllByPetId(petId);
    }

    public Medicines findAllMedicinesByPetId(final UUID petId, final UUID userId) {
        throwIfUserOrPetDoesNotExist(userId, petId);
        return new Medicines(medicineRepository.findAllMedicinesByPetId(petId));
    }

    private Optional<Medicine> findMedicineById(final UUID medicineId) {
        return medicineRepository.findById(medicineId);
    }

    public Medicine findExistingMedicineById(final UUID medicineId, UUID petId, UUID userId) {
        throwIfUserOrPetDoesNotExist(userId, petId);
        return findMedicineById(medicineId).orElseThrow(() ->
                new ResourceNotFoundException("Medicine with given id does not exist."));
    }

    private void throwIfUserOrPetDoesNotExist(final UUID userId, final UUID petId) {
        petService.findExistingPetById(petId);
        userService.findExistingUser(userId);
    }

    public Medicine updateMedicine(final UUID medicineId, final Medicine medicine) {
        findMedicineById(medicineId);
        medicine.setId(medicineId);
        return medicineRepository.save(medicine);
    }
}
