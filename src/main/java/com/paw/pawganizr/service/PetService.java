package com.paw.pawganizr.service;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.model.Pet;
import com.paw.pawganizr.repository.PetRepository;
import com.paw.pawganizr.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PetRepository petRepository;

    public void save(final Pet pet, final Long userId) {
        final Optional<AppUser> optionalPetUser = userRepository.findById(userId);
        if (optionalPetUser.isPresent()) {
            pet.setOwner(optionalPetUser.get());
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
