package com.paw.pawganizr.pedigree;

import com.paw.pawganizr.pet.Pet;
import com.paw.pawganizr.pet.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Long;

import static java.util.Objects.isNull;

@Service
@Transactional
public class PedigreeService {

    private final PedigreeRepository pedigreeRepository;
    private final PetService petService;


    public PedigreeService(PedigreeRepository pedigreeRepository, PetService petService) {
        this.pedigreeRepository = pedigreeRepository;
        this.petService = petService;
    }

    public Optional<Pedigree> findPedigree(final Long petId) {
        return pedigreeRepository.findByPetId(petId);
    }

    public void deletePedigree(final Long petId) {
        pedigreeRepository.deleteAllByPetId(petId);
    }

    public void saveOrUpdate(final Long petId, final Pedigree updatedPedigree) {
        Pet pet = petService.findExistingPetById(petId);
        Pedigree existingPedigree = findExistingPedigree(petId);
        if (isNull(existingPedigree)) existingPedigree = new Pedigree();
        existingPedigree.setBreeder(updatedPedigree.getBreeder());
        existingPedigree.setFatherName(updatedPedigree.getFatherName());
        existingPedigree.setMotherName(updatedPedigree.getMotherName());
        existingPedigree.setPedigreeNumber(updatedPedigree.getPedigreeNumber());
        existingPedigree.setPet(pet);
        pedigreeRepository.save(existingPedigree);
    }

    public Pedigree findExistingPedigree(final Long petId) {
        return findPedigree(petId).orElse(null);
    }
}
