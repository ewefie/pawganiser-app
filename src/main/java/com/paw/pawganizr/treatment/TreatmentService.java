package com.paw.pawganizr.treatment;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.pet.PetMapper;
import com.paw.pawganizr.pet.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TreatmentService {
    private final PetService petService;
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(final PetService petService, final TreatmentRepository treatmentRepository) {
        this.petService = petService;
        this.treatmentRepository = treatmentRepository;
    }

    public TreatmentDto saveTreatment(final TreatmentDto treatmentDto, final Long petId, final Long userId) {
        var existingPet = petService.getPetById(petId, userId);
        var treatmentToSave = TreatmentMapper.INSTANCE.dtoToTreatment(treatmentDto);
        treatmentToSave.setPet(PetMapper.INSTANCE.dtoToPet(existingPet));
        treatmentRepository.save(treatmentToSave);
        return TreatmentMapper.INSTANCE.treatmentToDto(treatmentToSave);
    }

    Treatment getExistingTreatmentById(final Long treatmentId) {
        return treatmentRepository.findById(treatmentId).orElseThrow(() -> new ResourceNotFoundException("Treatment with given id does not exist"));
    }

    public TreatmentDto getTreatmentById(final Long treatmentId, final Long petId, final Long userId) {
        petService.getPetById(petId, userId); //this method will throw an exception if user won't have access to treatment
        var existingTreatment = getExistingTreatmentById(treatmentId);
        return TreatmentMapper.INSTANCE.treatmentToDto(existingTreatment);
    }

    //todo: getAllTreatmentsByPetId(); deleteTreatmentById();
    // deleteAllTreatmentsByPetId(); updateTreatment();

//
//    public void deleteById(final Long treatmentId) {
//        treatmentRepository.deleteById(treatmentId);
//    }
//
//    public Treatment updateTreatment(final Long treatmentId, final Treatment updatedTreatment) {
//        Treatment existingTreatment = findExistingTreatmentById(treatmentId);
//        existingTreatment.setDescription(updatedTreatment.getDescription());
//        existingTreatment.setTreatmentEndDate(updatedTreatment.getTreatmentEndDate());
//        existingTreatment.setTreatmentStartDate(updatedTreatment.getTreatmentStartDate());
//        existingTreatment.setType(updatedTreatment.getType());
//        return treatmentRepository.save(existingTreatment);
//    }
}
