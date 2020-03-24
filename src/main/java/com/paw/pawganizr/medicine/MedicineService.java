package com.paw.pawganizr.medicine;

import com.paw.pawganizr.pet.Pet;
import com.paw.pawganizr.pet.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Long;

@Service
@Transactional
public class MedicineService {

    private final PetService petService;
    private final MedicineRepository medicineRepository;
    private final AppUserService appUserService;


    public MedicineService(PetService petService, MedicineRepository medicineRepository, AppUserService appUserService) {
        this.petService = petService;
        this.medicineRepository = medicineRepository;
        this.appUserService = appUserService;
    }

    public Medicine addMedicineToPet(final Long petId, final Medicine medicineToAdd) {
        final Pet existingPet = petService.findExistingPetById(petId);
        medicineToAdd.setPet(existingPet);
        return medicineRepository.save(medicineToAdd);
    }

    public void deleteById(final Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    public void deleteAllMedicines(final Long petId) {
        petService.findExistingPetById(petId);
        medicineRepository.deleteAllByPetId(petId);
    }

    public Medicines findAllMedicinesByPetId(final Long petId) {
        throwIfPetDoesNotExist(petId);
        return new Medicines(medicineRepository.findAllByPetId(petId));
    }

    private Optional<Medicine> findMedicineById(final Long medicineId) {
        return medicineRepository.findById(medicineId);
    }

    public Medicine findExistingMedicineById(final Long medicineId) {
        return findMedicineById(medicineId).orElseThrow(() ->
                new ResourceNotFoundException("Medicine with given id does not exist."));
    }

    private void throwIfPetDoesNotExist(final Long petId) {
        petService.findExistingPetById(petId);
    }

    public Medicine updateMedicine(final Long medicineId, final Medicine medicine) {
        final Medicine existingMedicine = findExistingMedicineById(medicineId);
        existingMedicine.setDosage(medicine.getDosage());
        existingMedicine.setImportancy(medicine.getImportancy());
        existingMedicine.setName(medicine.getName());
        return medicineRepository.save(existingMedicine);
    }
}
