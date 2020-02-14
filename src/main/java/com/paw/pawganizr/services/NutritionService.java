package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.Nutrition;
import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.repositories.NutritionRepository;
import com.paw.pawganizr.wrappers.Nutrients;
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

    public Nutrition addNutritionToPet(final UUID petId, final Nutrition nutrition) {
        final Pet existingPet = petService.findExistingPetById(petId);
        nutrition.setPet(existingPet);
        return nutritionRepository.save(nutrition);
    }

    public Nutrition updateNutrition(final UUID petId, final UUID nutritionId, final Nutrition updatedNutrition) {
        final Nutrition existingNutrition = findExistingNutritionById(nutritionId);
        existingNutrition.setBrand(updatedNutrition.getBrand());
        existingNutrition.setDescription(updatedNutrition.getDescription());
        existingNutrition.setFoodName(updatedNutrition.getFoodName());
        return nutritionRepository.save(existingNutrition);
    }

    public void deleteById(final UUID id) {
        nutritionRepository.deleteById(id);
    }

    public void deleteAllNutrients(final UUID petId) {
        petService.throwIfPetDoesNotExist(petId);
        nutritionRepository.deleteAllByPetId(petId);
    }

    public Optional<Nutrition> findById(final UUID id) {
        return nutritionRepository.findById(id);
    }
//
//    public Nutrition findExistingNutritionById(final UUID nutritionId, final UUID petId, final UUID userId) {
//        petService.throwIfUserOrPetDoesNotExist(userId, petId);
//        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
//    }

    public Nutrition findExistingNutritionById(final UUID nutritionId) {
        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }

    public Nutrition findExistingNutritionById(final UUID nutritionId, final UUID petId) {
        petService.throwIfPetDoesNotExist(petId);
        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }

    public Nutrients findAllNutrientsByPetId(final UUID petId) {
        petService.throwIfPetDoesNotExist(petId);
        return new Nutrients(nutritionRepository.findAllByPetId(petId));
    }
}
