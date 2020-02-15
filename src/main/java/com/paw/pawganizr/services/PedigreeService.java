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
        pedigreeRepository.deleteByPetId(petId);
    }

    public Pedigree addPedigree(final UUID petId, final Pedigree pedigree) {
        Pet pet = petService.findExistingPetById(petId);
        pet.setPedigree(pedigree);
        pedigree.setPet(pet);
        return pedigreeRepository.save(pedigree);
    }

    public void updatePedigree(final UUID petId, final Pedigree updatedPedigree) {
//        Pet pet = petService.findExistingPetById(petId);
//        Pedigree existingPedigree = pet.getPedigree();
        Pedigree existingPedigree = findExistingPedigree(petId);
        existingPedigree.setBreeder(updatedPedigree.getBreeder());
        existingPedigree.setFatherName(updatedPedigree.getFatherName());
        existingPedigree.setMotherName(updatedPedigree.getMotherName());
        existingPedigree.setPedigreeNum(updatedPedigree.getPedigreeNum());
        pedigreeRepository.save(existingPedigree);
    }

    public Pedigree findExistingPedigree(final UUID petId) {
        return findPedigree(petId).orElseThrow(() -> new ResourceNotFoundException("Pet does not have pedigree yet."));
    }
}
