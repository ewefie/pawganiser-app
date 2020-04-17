package com.paw.pawganizr.pet;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.user.AppUserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class PetService {
    private final AppUserService appUserService;
    private final PetRepository petRepository;

    public PetService(final AppUserService appUserService, final PetRepository petRepository) {
        this.appUserService = appUserService;
        this.petRepository = petRepository;
    }

    public PetDto savePet(final PetDto petDto, final Long userId) {
        var existingUser = appUserService.getExistingUser(userId);
        var petToSave = PetMapper.INSTANCE.dtoToPet(petDto);
        petToSave.setOwner(existingUser);
        petRepository.save(petToSave);
        return PetMapper.INSTANCE.petToDto(petToSave);
    }

    Pet getExistingPetById(final Long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet with given id does not exist"));
    }

    public Pets getAllPetsByUserId(final Long userId) {
        var petList = petRepository.findAllByOwnerId(userId).stream() //todo: check if this method works
                .map(PetMapper.INSTANCE::petToDto)
                .collect(Collectors.toList());
        return new Pets(petList);
    }

    public PetDto getPetById(final Long petId, final Long userId) {
        var existingPet = getExistingPetById(petId);
        if (existingPet.getOwner().getId().equals(userId)) {
            return PetMapper.INSTANCE.petToDto(existingPet);
        }
        throw new AccessDeniedException("You do not have permission to access this content");
    }

    public void deleteAllPetsByUserId(final Long userId) {
        petRepository.deleteAllByOwnerId(userId);
    }

    public void deletePetById(final Long petId, final Long userId) {
        var existingPet = getExistingPetById(petId);
        if (!existingPet.getOwner().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to access this content");

        }
        petRepository.deleteById(petId);
    }

    public PetDto updatePet(final Long petId, final Pet updatedPet, final Long userId) {
        var existingPet = getPetById(petId, userId);
        existingPet.setPetName(updatedPet.getPetName());
        existingPet.setGender(updatedPet.getGender());
        existingPet.setColor(updatedPet.getColor());
        existingPet.setBirthDate(updatedPet.getBirthDate());
        existingPet.setDead(updatedPet.isDead());
        existingPet.setDeathDate(updatedPet.getDeathDate());
        existingPet.setPetAvatarUrl(updatedPet.getPetAvatarUrl());
        existingPet.setChipNumber(updatedPet.getChipNumber());
        existingPet.setRace(updatedPet.getRace());
        existingPet.setType(updatedPet.getType());
        petRepository.save(PetMapper.INSTANCE.dtoToPet(existingPet));
        return existingPet;
    }
}
