package com.paw.pawganizr.services;

import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.PetRepository;
import com.paw.pawganizr.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PetService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PetRepository petRepository;

    public PetService(UserRepository userRepository, UserService userService, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.petRepository = petRepository;
    }

    public void addPetToUser(final Pet pet, final UUID id) {
        final AppUser existingUser = userService.findExistingUser(id);
        pet.setOwner(existingUser);
        petRepository.save(pet);
    }

    public Optional<Pet> findPetById(final UUID id) {
        return petRepository.findById(id);
    }

    public void delete(final UUID id) {
        petRepository.deleteById(id);
    }

    public List<Pet> findAllPets(final UUID appUserId) {
        final AppUser existingUser = userService.findExistingUser(appUserId);
        return petRepository.findAllByOwner(existingUser);
    }


}
