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

    final PetService petService;
    final NutritionRepository nutritionRepository;

    public NutritionService(PetService petService, NutritionRepository nutritionRepository) {
        this.petService = petService;
        this.nutritionRepository = nutritionRepository;
    }

    public Nutrition addNutritionToPet(final UUID petId, final Nutrition nutrition) {
        final Pet existingPet = petService.findExistingPetById(petId);
        nutrition.setPet(existingPet);
        return nutritionRepository.save(nutrition);
    }

    public Nutrition updateNutrition(final UUID nutritionId, final Nutrition updatedNutrition) {
        findExistingNutritionById(nutritionId);
        updatedNutrition.setId(nutritionId);
        return nutritionRepository.save(updatedNutrition);
    }

    public void deleteNutritionById(final UUID id) {
        nutritionRepository.deleteById(id);
    }

    public Optional<Nutrition> findById(final UUID id) {
        return nutritionRepository.findById(id);
    }

    public Nutrition findExistingNutritionById(final UUID id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }

    public Nutritions findAllNutritionsByPetId(final UUID id) {
        petService.findExistingPetById(id);
        return new Nutritions(nutritionRepository.findAllByPetId(id));
    }

}
