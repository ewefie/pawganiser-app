package com.paw.pawganizr.service;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.model.Pet;
import com.paw.pawganizr.repository.PetRepository;
import com.paw.pawganizr.repository.UserRepository;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PetService {
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public PetService(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    public void save(final Pet pet, final UUID userId) {
        final Optional<AppUser> optionalPetUser = userRepository.findById(userId);
        if (optionalPetUser.isPresent()) {
//            pet.setOwner(optionalPetUser.get());
            petRepository.save(pet);
        }
    }

    public Optional<Pet> findPetById(final UUID id) {
        return petRepository.findById(id);
    }

    public void delete(final UUID id) {
        petRepository.deleteById(id);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

}
