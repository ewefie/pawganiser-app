package com.paw.pawganizr.services;

import com.paw.pawganizr.models.Pedigree;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.PedigreeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Pedigree findPedigree(final UUID petId) {
        return pedigreeRepository.findByPetId(petId);
    }

    public void deletePedigree(final UUID petId) {
        pedigreeRepository.deleteByPetId(petId);
    }

    public Pedigree addPedigree(final UUID petId, final Pedigree pedigree) {
        Pet pet = petService.findExistingPetById(petId);
        pedigree.setPet(pet);
        return pedigreeRepository.save(pedigree);
    }

    public Pedigree updatePedigree(final UUID petId, final Pedigree pedigree) {
        Pet pet = petService.findExistingPetById(petId);
        Pedigree oldPedigree = pet.getPedigree();
        oldPedigree.setBreeder(pedigree.getBreeder());
        oldPedigree.setFatherName(pedigree.getFatherName());
        oldPedigree.setMotherName(pedigree.getMotherName());
        oldPedigree.setPedigreeNum(pedigree.getPedigreeNum());
        return pedigreeRepository.save(oldPedigree);
    }
}