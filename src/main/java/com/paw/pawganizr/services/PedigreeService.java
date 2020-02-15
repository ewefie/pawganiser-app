package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.Pedigree;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.PedigreeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PedigreeService {

    private final PedigreeRepository pedigreeRepository;
    private final PetService petService;


    public PedigreeService(PedigreeRepository pedigreeRepository, PetService petService) {
        this.pedigreeRepository = pedigreeRepository;
        this.petService = petService;
    }

    public Optional<Pedigree> findPedigree(final UUID petId) {
        return pedigreeRepository.findByPetId(petId);
    }

    public void deletePedigree(final UUID petId) {
        pedigreeRepository.deleteAllByPetId(petId);
    }

    public void saveOrUpdate(final UUID petId, final Pedigree updatedPedigree) {
        Pet pet = petService.findExistingPetById(petId);
        Pedigree existingPedigree = findExistingPedigree(petId);
        existingPedigree.setBreeder(updatedPedigree.getBreeder());
        existingPedigree.setFatherName(updatedPedigree.getFatherName());
        existingPedigree.setMotherName(updatedPedigree.getMotherName());
        existingPedigree.setPedigreeNumber(updatedPedigree.getPedigreeNumber());
        existingPedigree.setPet(pet);
        pedigreeRepository.save(existingPedigree);
    }

    public Pedigree findExistingPedigree(final UUID petId) {
        return findPedigree(petId).orElse(new Pedigree());
    }
}
