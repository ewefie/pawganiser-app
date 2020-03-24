package com.paw.pawganizr.nutrition;

import com.paw.pawganizr.pet.Pet;
import com.paw.pawganizr.pet.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Long;

@Service
@Transactional
public class NutritionService {
    private final AppUserService appUserService;
    final PetService petService;
    final NutritionRepository nutritionRepository;


    public NutritionService(AppUserService appUserService, PetService petService, NutritionRepository nutritionRepository) {
        this.appUserService = appUserService;
        this.petService = petService;
        this.nutritionRepository = nutritionRepository;
    }

    public Nutrition addNutritionToPet(final Long petId, final Nutrition nutrition) {
        final Pet existingPet = petService.findExistingPetById(petId);
        nutrition.setPet(existingPet);
        return nutritionRepository.save(nutrition);
    }

    public Nutrition updateNutrition(final Long petId, final Long nutritionId, final Nutrition updatedNutrition) {
        final Nutrition existingNutrition = findExistingNutritionById(nutritionId);
        existingNutrition.setBrand(updatedNutrition.getBrand());
        existingNutrition.setDescription(updatedNutrition.getDescription());
        existingNutrition.setFoodName(updatedNutrition.getFoodName());
        return nutritionRepository.save(existingNutrition);
    }

    public void deleteById(final Long id) {
        nutritionRepository.deleteById(id);
    }

    public void deleteAllNutrients(final Long petId) {
        petService.throwIfPetDoesNotExist(petId);
        nutritionRepository.deleteAllByPetId(petId);
    }

    public Optional<Nutrition> findById(final Long id) {
        return nutritionRepository.findById(id);
    }
//
//    public Nutrition findExistingNutritionById(final Long nutritionId, final Long petId, final Long userId) {
//        petService.throwIfUserOrPetDoesNotExist(userId, petId);
//        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
//    }

    public Nutrition findExistingNutritionById(final Long nutritionId) {
        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }

    public Nutrition findExistingNutritionById(final Long nutritionId, final Long petId) {
        petService.throwIfPetDoesNotExist(petId);
        return findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }

    public Nutrients findAllNutrientsByPetId(final Long petId) {
        petService.throwIfPetDoesNotExist(petId);
        return new Nutrients(nutritionRepository.findAllByPetId(petId));
    }
}
