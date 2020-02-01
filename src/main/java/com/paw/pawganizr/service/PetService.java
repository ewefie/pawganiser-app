package com.paw.pawganizr.service;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.model.Pet;
import com.paw.pawganizr.repository.PetRepository;
import com.paw.pawganizr.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public PetService(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    public void save(final Pet pet, final Long userId) {
        final Optional<AppUser> optionalPetUser = userRepository.findById(userId);
        if (optionalPetUser.isPresent()) {
//            pet.setOwner(optionalPetUser.get());
            petRepository.save(pet);
        }
    }

    public Optional<Pet> findPetById(final Long id) {
        return petRepository.findById(id);
    }

    public void delete(final Long id) {
        petRepository.deleteById(id);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

}
