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
      return  petRepository.save(pet);
    }

    public Optional<Pet> findPetById(final UUID id) {
        return petRepository.findById(id);
    }

    public void delete(final UUID id) {
        petRepository.deleteById(id);
    }

    private List<Pet> findAllPetsByUserId(final UUID appUserId) {
        final AppUser existingUser = userService.findExistingUser(appUserId);
        return petRepository.findAllByOwner(existingUser);
    }

    public BasicPetInfos getBasicPetInfoByUserId(final UUID id) {
        final List<BasicPetInfo> petInfos = findAllPetsByUserId(id).stream()
                .map(pet -> mapper.mapPetToBasicPetInfo(pet))
                .collect(Collectors.toList());
        return new BasicPetInfos(petInfos);
    }

    public Pet findExistingPetById(final UUID petId) {
        return findPetById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet with given id does not exist"));
    }
//todo: validate pet data
    //update partially
}
