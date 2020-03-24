package com.paw.pawganizr.treatment;

import com.paw.pawganizr.pet.Pet;
import com.paw.pawganizr.pet.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Long;

@Service
@Transactional
public class TreatmentService {
    private final AppUserService appUserService;
    private final PetService petService;
    private final TreatmentRepository treatmentRepository;


    public TreatmentService(AppUserService appUserService, PetService petService, TreatmentRepository treatmentRepository) {
        this.appUserService = appUserService;
        this.petService = petService;
        this.treatmentRepository = treatmentRepository;
    }

    public Treatment addTreatmentToPet(final Long petId, final Treatment treatmentToAdd) {
        final Pet existingPet = petService.findExistingPetById(petId);
        treatmentToAdd.setPet(existingPet);
        return treatmentRepository.save(treatmentToAdd);
    }

    public void deleteById(final Long treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }

    public Treatment updateTreatment(final Long treatmentId, final Treatment updatedTreatment) {
        Treatment existingTreatment = findExistingTreatmentById(treatmentId);
        existingTreatment.setDescription(updatedTreatment.getDescription());
        existingTreatment.setTreatmentEndDate(updatedTreatment.getTreatmentEndDate());
        existingTreatment.setTreatmentStartDate(updatedTreatment.getTreatmentStartDate());
        existingTreatment.setType(updatedTreatment.getType());
        return treatmentRepository.save(existingTreatment);
    }

    public Treatment findExistingTreatmentById(final Long treatmentId) {
        return findTreatmentById(treatmentId).orElseThrow(() -> new ResourceNotFoundException("Treatment with given id does not exist"));
    }

    private Optional<Treatment> findTreatmentById(final Long id) {
        return treatmentRepository.findById(id);
    }

    public Treatments findAllTreatmentsByPetId(final Long petId) {
        petService.throwIfPetDoesNotExist(petId);
        return new Treatments(treatmentRepository.findAllByPetId(petId));
    }

    public void deleteAllTreatments(final Long petId) {
        petService.findExistingPetById(petId);
        treatmentRepository.deleteAllByPetId(petId);
    }
}
