package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.Nutrition;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.NutritionRepository;
import com.paw.pawganizr.wrappers.Nutritions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class NutritionService {
    private final UserService userService;
    final PetService petService;
    final NutritionRepository nutritionRepository;

    public NutritionService(UserService userService, PetService petService, NutritionRepository nutritionRepository) {
        this.userService = userService;
        this.petService = petService;
        this.nutritionRepository = nutritionRepository;
    }

    public Nutrition addNutritionToPet(final UUID userId, final UUID petId, final Nutrition nutrition) {
        userService.findExistingUser(userId);
        final Pet existingPet = petService.findExistingPetById(petId);
        nutrition.setPet(existingPet);
        return nutritionRepository.save(nutrition);
    }

    public Nutrition updateNutrition(final UUID userId, final UUID petId, final UUID nutritionId, final Nutrition updatedNutrition) {
        findExistingNutritionById(nutritionId, petId, userId);
        updatedNutrition.setId(nutritionId);
        return nutritionRepository.save(updatedNutrition);
    }

    public void deleteById(final UUID id) {
        nutritionRepository.deleteById(id);
    }

    public void deleteAllNutritions(final UUID petId, final UUID userId) {
        petService.throwIfUserOrPetDoesNotExist(userId, petId);
        nutritionRepository.deleteAllByPetId(petId);
    }

    public Optional<Nutrition> findById(final UUID id) {
        return nutritionRepository.findById(id);
    }

    public Nutrition findExistingNutritionById(final UUID nutritionId, final UUID petId, final UUID userId) {
        petService.throwIfUserOrPetDoesNotExist(userId, petId);
        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }

    public Nutritions findAllNutritionsByPetId(final UUID petId, final UUID userId) {
        petService.throwIfUserOrPetDoesNotExist(userId, petId);
        return new Nutritions(nutritionRepository.findAllByPetId(petId));
    }
}
