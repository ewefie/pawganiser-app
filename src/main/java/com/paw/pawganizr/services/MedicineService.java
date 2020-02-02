package com.paw.pawganizr.services;

import com.paw.pawganizr.models.Medicine;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.MedicineRepository;
import com.paw.pawganizr.wrappers.Medicines;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class MedicineService {

    private final PetService petService;
    private final MedicineRepository medicineRepository;


    public MedicineService(PetService petService, MedicineRepository medicineRepository) {
        this.petService = petService;
        this.medicineRepository = medicineRepository;
    }

    public Medicine addMedicineToPet(final UUID petId, final Medicine medicineToAdd) {
        final Pet existingPet = petService.findExistingPetById(petId);
        medicineToAdd.setPet(existingPet);
        return medicineRepository.save(medicineToAdd);
    }

    public void deleteById(final UUID medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    public Medicines findAllMedicinesByPetId(final UUID id) {
        petService.findExistingPetById(id);
        return new Medicines(medicineRepository.findAllMedicinesByPetId(id));
    }
}
