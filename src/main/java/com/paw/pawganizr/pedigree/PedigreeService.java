package com.paw.pawganizr.pedigree;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.pet.PetMapper;
import com.paw.pawganizr.pet.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedigreeService {
    private final PetService petService;
    private final PedigreeRepository pedigreeRepository;

    public PedigreeService(final PetService petService, final PedigreeRepository pedigreeRepository) {
        this.petService = petService;
        this.pedigreeRepository = pedigreeRepository;
    }

    public PedigreeDto getPedigree(final Long petId, final Long userId) {
        return PedigreeMapper.INSTANCE.pedigreeToDto(returnPedigreeIfUserHaveAccess(petId, userId));
    }

    public void deletePedigree(final Long petId, final Long userId) {
        returnPedigreeIfUserHaveAccess(petId, userId);
        pedigreeRepository.deleteByPetId(petId);
    }

    public PedigreeDto savePedigree(final PedigreeDto pedigreeDto, final Long petId, final Long userId) {
        var existingPet = petService.getPetById(petId, userId);
        var pedigreeToSave = PedigreeMapper.INSTANCE.dtoToPedigree(pedigreeDto);
        pedigreeToSave.setPet(PetMapper.INSTANCE.dtoToPet(existingPet));
        pedigreeRepository.save(pedigreeToSave);
        return PedigreeMapper.INSTANCE.pedigreeToDto(pedigreeToSave);
    }

    public PedigreeDto updatePedigree(final PedigreeDto updatedPedigree, final Long petId, final Long userId) {
        var existingPedigree = returnPedigreeIfUserHaveAccess(petId, userId);
        existingPedigree.setPedigreeNumber(updatedPedigree.getPedigreeNumber());
        existingPedigree.setBreeder(updatedPedigree.getBreeder());
        existingPedigree.setFatherName(updatedPedigree.getFatherName());
        existingPedigree.setMotherName(updatedPedigree.getMotherName());
        pedigreeRepository.save(existingPedigree);
        return PedigreeMapper.INSTANCE.pedigreeToDto(existingPedigree);
    }

    private Pedigree returnPedigreeIfUserHaveAccess(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        return getExistingPedigreeByPetId(petId);
    }

    private Pedigree getExistingPedigreeByPetId(final Long petId) {
        return pedigreeRepository.findByPetId(petId).orElseThrow(() -> new ResourceNotFoundException("This pet does not have a pedigree"));
    }
}

