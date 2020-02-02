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
    private final PetService petService;
    private final TreatmentRepository treatmentRepository;


    public TreatmentService(PetService petService, TreatmentRepository treatmentRepository) {
        this.petService = petService;
        this.treatmentRepository = treatmentRepository;
    }

    public Treatment addTreatmentToPet(final UUID petId, final Treatment treatmentToAdd) {
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


    public Treatment findExistingTreatmentById(final UUID id) {
        return findTreatmentById(id).orElseThrow(() -> new ResourceNotFoundException("Treatment with given id does not exist"));
    }

    public Optional<Treatment> findTreatmentById(final UUID id) {
        return treatmentRepository.findById(id);
    }

    public Treatments findAllTreatmentsByPetId(final UUID id) {
        petService.findExistingPetById(id);
        return new Treatments(treatmentRepository.findAllTreatmentsByPetId(id));
    }


}
