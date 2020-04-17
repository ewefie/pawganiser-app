package com.paw.pawganizr.treatment;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.pet.PetMapper;
import com.paw.pawganizr.pet.PetService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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

    public TreatmentDto getTreatmentById(final Long treatmentId, final Long petId, final Long userId) {
        var existingTreatment = returnTreatmentIfUserHaveAccess(treatmentId, petId, userId);
        return TreatmentMapper.INSTANCE.treatmentToDto(existingTreatment);
    }

    public Treatments getAllTreatmentsByPetId(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        var petTreatments = treatmentRepository.findAllByPetId(petId).stream()
                .map(TreatmentMapper.INSTANCE::treatmentToDto)
                .collect(Collectors.toList());
        return new Treatments(petTreatments);
    }

    public void deleteAllTreatmentsByPetId(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        treatmentRepository.deleteAllByPetId(petId);
    }

    public void deleteTreatmentById(final Long treatmentId, final Long petId, final Long userId) {
        returnTreatmentIfUserHaveAccess(treatmentId, petId, userId);
        treatmentRepository.deleteById(treatmentId);
    }

    public TreatmentDto updateTreatment(final TreatmentDto updatedTreatment, final Long treatmentId, final Long petId, final Long userId) {
        var existingTreatment = returnTreatmentIfUserHaveAccess(treatmentId, petId, userId);
        existingTreatment.setDescription(updatedTreatment.getDescription());
        existingTreatment.setTreatmentEndDate(updatedTreatment.getTreatmentEndDate());
        existingTreatment.setTreatmentStartDate(updatedTreatment.getTreatmentStartDate());
        existingTreatment.setType(updatedTreatment.getType());
        treatmentRepository.save(existingTreatment);
        return TreatmentMapper.INSTANCE.treatmentToDto(existingTreatment);
    }

    //fixme: find better name
    private Treatment returnTreatmentIfUserHaveAccess(final Long treatmentId, final Long petId, final Long userId) {
        //check if pet with given id belongs to user, if not then throw an error Access denied
        petService.getPetById(petId, userId);
        var treatment = getExistingTreatmentById(treatmentId);
        //check if treatment with given id belongs to pet, if not then throw an error ...
        if (!treatment.getPet().getId().equals(petId)) {
            // todo: a new error class
            throw new AccessDeniedException("Pet with given id " + petId + " does not own a treatment with id " + treatmentId);
        }
        return getExistingTreatmentById(treatmentId);
    }

    private Treatment getExistingTreatmentById(final Long treatmentId) {
        return treatmentRepository.findById(treatmentId).orElseThrow(() -> new ResourceNotFoundException("Treatment with given id does not exist"));
    }
}
