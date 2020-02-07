package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.models.Treatment;
import com.paw.pawganizr.repositories.TreatmentRepository;
import com.paw.pawganizr.wrappers.Treatments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TreatmentService {
    private final UserService userService;
    private final PetService petService;
    private final TreatmentRepository treatmentRepository;


    public TreatmentService(UserService userService, PetService petService, TreatmentRepository treatmentRepository) {
        this.userService = userService;
        this.petService = petService;
        this.treatmentRepository = treatmentRepository;
    }

    public Treatment addTreatmentToPet(final UUID userId, final UUID petId, final Treatment treatmentToAdd) {
        userService.findExistingUser(userId);
        final Pet existingPet = petService.findExistingPetById(petId);
        treatmentToAdd.setPet(existingPet);
        return treatmentRepository.save(treatmentToAdd);
    }

    public void deleteById(final UUID treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }

    public Treatment updateTreatment(final UUID treatmentId, final Treatment updatedTreatment) {
        findTreatmentById(treatmentId);
        updatedTreatment.setId(treatmentId);
        return treatmentRepository.save(updatedTreatment);
    }

    public Treatment findExistingTreatmentById(final UUID treatmentId, final UUID petId, final UUID userId) {
        petService.throwIfUserOrPetDoesNotExist(userId, petId);
        return findTreatmentById(treatmentId).orElseThrow(() -> new ResourceNotFoundException("Treatment with given id does not exist"));
    }

    private Optional<Treatment> findTreatmentById(final UUID id) {
        return treatmentRepository.findById(id);
    }

    public Treatments findAllTreatmentsByPetId(final UUID petId, final UUID userId) {
        petService.throwIfUserOrPetDoesNotExist(userId, petId);
        return new Treatments(treatmentRepository.findAllByPetId(petId));
    }


    public void deleteAllTreatments(final UUID petId) {
        petService.findExistingPetById(petId);
        treatmentRepository.deleteAllByPetId(petId);
    }
}
