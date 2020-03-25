package com.paw.pawganizr.medicine;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.pet.PetMapper;
import com.paw.pawganizr.pet.PetService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class MedicineService {
    private final PetService petService;
    private final MedicineRepository medicineRepository;

    public MedicineService(final PetService petService, final MedicineRepository medicineRepository) {
        this.petService = petService;
        this.medicineRepository = medicineRepository;
    }

    public MedicineDto saveMedicine(final MedicineDto medicineDto, final Long petId, final Long userId) {
        var existingPet = petService.getPetById(petId, userId);
        var medicineToSave = MedicineMapper.INSTANCE.dtoToMedicine(medicineDto);
        medicineToSave.setPet(PetMapper.INSTANCE.dtoToPet(existingPet));
        medicineRepository.save(medicineToSave);
        return MedicineMapper.INSTANCE.medicineToDto(medicineToSave);
    }

    public MedicineDto getMedicineById(final Long medicineId, final Long petId, final Long userId) {
        var existingMedicine = returnMedicineIfUserHaveAccess(medicineId, petId, userId);
        return MedicineMapper.INSTANCE.medicineToDto(existingMedicine);
    }

    public Medicines getAllMedicinesByPetId(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        var petMedicines = medicineRepository.findAllByPetId(petId).stream()
                .map(MedicineMapper.INSTANCE::medicineToDto)
                .collect(Collectors.toList());
        return new Medicines(petMedicines);
    }

    public void deleteAllMedicinesByPetId(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        medicineRepository.deleteAllByPetId(petId);
    }

    public void deleteMedicineById(final Long medicineId, final Long petId, final Long userId) {
        returnMedicineIfUserHaveAccess(medicineId, petId, userId);
        medicineRepository.deleteById(medicineId);
    }

    public MedicineDto updateMedicine(final MedicineDto updatedMedicine, final Long medicineId, final Long petId, final Long userId) {
        var existingMedicine = returnMedicineIfUserHaveAccess(medicineId, petId, userId);
        existingMedicine.setName(updatedMedicine.getName());
        existingMedicine.setDosage(updatedMedicine.getDosage());
        existingMedicine.setImportancy(updatedMedicine.getImportancy());
        medicineRepository.save(existingMedicine);
        return MedicineMapper.INSTANCE.medicineToDto(existingMedicine);
    }

    //fixme: find better name
    private Medicine returnMedicineIfUserHaveAccess(final Long medicineId, final Long petId, final Long userId) {
        //check if pet with given id belongs to user, if not then throw an error Access denied
        petService.getPetById(petId, userId);
        var medicine = getExistingMedicineById(medicineId);
        //check if medicine with given id belongs to pet, if not then throw an error ...
        if (!medicine.getPet().getId().equals(petId)) {
            // todo: a new error class
            throw new AccessDeniedException("Pet with given id " + petId + " does not own a medicine with id " + medicineId);
        }
        return getExistingMedicineById(medicineId);
    }

    private Medicine getExistingMedicineById(final Long medicineId) {
        return medicineRepository.findById(medicineId).orElseThrow(() -> new ResourceNotFoundException("Medicine with given id does not exist"));
    }
}
