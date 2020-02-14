package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.BasicPetInfo;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.PetRepository;
import com.paw.pawganizr.repositories.UserRepository;
import com.paw.pawganizr.wrappers.BasicPetInfos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PetRepository petRepository;
    private final PetToBasicPetInfoMapper mapper;

    public PetService(UserRepository userRepository, UserService userService, PetRepository petRepository, PetToBasicPetInfoMapper mapper) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    public Pet addPetToUser(final Pet pet, final UUID id) {
        final AppUser existingUser = userService.findExistingUser(id);
        pet.setOwner(existingUser);
        return petRepository.save(pet);
    }

    public void deleteAllPetsByUserId(final UUID userId) {
        userService.findExistingUser(userId);
        petRepository.deleteAllByOwnerId(userId);
    }

    private List<Pet> findAllPetsByUserId(final UUID appUserId) {
        final AppUser existingUser = userService.findExistingUser(appUserId);
        return petRepository.findAllByOwner(existingUser);
    }

    public BasicPetInfos getBasicPetInfoByUserId(final UUID id) {
        final List<BasicPetInfo> petInfos = findAllPetsByUserId(id).stream()
                .map(mapper::mapPetToBasicPetInfo)
                .collect(Collectors.toList());
        return new BasicPetInfos(petInfos);
    }

    public Pet findExistingPetById(final UUID petId) {
        return findPetById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet with given id does not exist"));
    }

    public Pet updatePet(final UUID userId, final UUID petId, final Pet updatedPet) {
        throwIfUserOrPetDoesNotExist(userId, petId);
        updatedPet.setId(petId);
        return petRepository.save(updatedPet);
    }

    public void throwIfUserOrPetDoesNotExist(final UUID userId, final UUID petId) {
        findExistingPetById(petId);
        userService.findExistingUser(userId);
    }

    public Pet addPetToUser(final Pet pet, final Principal principal) {
        final UUID id = userService.getUserId(principal);
        final AppUser existingUser = userService.findExistingUser(id);
        pet.setOwner(existingUser);
        return petRepository.save(pet);
    }

    public Optional<Pet> findPetById(final UUID id) {
        return petRepository.findById(id);
    }

    public void deletePetById(final UUID id) {
        petRepository.deleteById(id);
    }

    public void deleteAllPetsByPrincipal(final Principal principal) {
        final UUID userId = userService.getUserId(principal);
        petRepository.deleteAllByOwnerId(userId);
    }

    private List<Pet> findAllPetsByPrincipal(final Principal principal) {
        final UUID userId = userService.getUserId(principal);
        final AppUser existingUser = userService.findExistingUser(userId);
        return petRepository.findAllByOwner(existingUser);
    }

    public BasicPetInfos getBasicPetInfoByPrincipal(final Principal principal) {
        final List<BasicPetInfo> petInfos = findAllPetsByPrincipal(principal).stream()
                .map(mapper::mapPetToBasicPetInfo)
                .collect(Collectors.toList());
        return new BasicPetInfos(petInfos);
    }

    public Pet updatePet(final UUID petId, final Pet updatedPet) {
        Pet existingPet = findExistingPetById(petId);
        existingPet.setPetName(updatedPet.getPetName());
        existingPet.setGender(updatedPet.getGender());
        existingPet.setColor(updatedPet.getColor());
        existingPet.setBirthDate(updatedPet.getBirthDate());
        existingPet.setDead(updatedPet.isDead());
        existingPet.setDeathDate(updatedPet.getDeathDate());
        existingPet.setRace(updatedPet.getRace());
        existingPet.sesType(updatedPet.getType());
        return petRepository.save(existingPet);
    }

//    public void throwIfUserOrPetDoesNotExist(final UUID userId, final UUID petId) {
//        findExistingPetById(petId);
//        userService.findExistingUser(userId);
//    }

    public void throwIfPetDoesNotExist(final UUID petId) {
        findExistingPetById(petId);
    }
}
