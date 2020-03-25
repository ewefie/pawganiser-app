package com.paw.pawganizr.nutrition;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.pet.PetMapper;
import com.paw.pawganizr.pet.PetService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class NutritionService {
    private final PetService petService;
    private final NutritionRepository nutritionRepository;

    public NutritionService(final PetService petService, final NutritionRepository nutritionRepository) {
        this.petService = petService;
        this.nutritionRepository = nutritionRepository;
    }

    public NutritionDto saveNutrition(final NutritionDto nutritionDto, final Long petId, final Long userId) {
        var existingPet = petService.getPetById(petId, userId);
        var nutritionToSave = NutritionMapper.INSTANCE.dtoToNutrition(nutritionDto);
        nutritionToSave.setPet(PetMapper.INSTANCE.dtoToPet(existingPet));
        nutritionRepository.save(nutritionToSave);
        return NutritionMapper.INSTANCE.nutritionToDto(nutritionToSave);
    }

    public NutritionDto getNutritionById(final Long nutritionId, final Long petId, final Long userId) {
        var existingNutrition = returnNutritionIfUserHaveAccess(nutritionId, petId, userId);
        return NutritionMapper.INSTANCE.nutritionToDto(existingNutrition);
    }

    public Nutrients getAllNutrientsByPetId(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        var petNutrients = nutritionRepository.findAllByPetId(petId).stream()
                .map(NutritionMapper.INSTANCE::nutritionToDto)
                .collect(Collectors.toList());
        return new Nutrients(petNutrients);
    }

    public void deleteAllNutrientsByPetId(final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        nutritionRepository.deleteAllByPetId(petId);
    }

    public void deleteNutritionById(final Long nutritionId, final Long petId, final Long userId) {
        returnNutritionIfUserHaveAccess(nutritionId, petId, userId);
        nutritionRepository.deleteById(nutritionId);
    }

    public NutritionDto updateNutrition(final NutritionDto updatedNutrition, final Long nutritionId, final Long petId, final Long userId) {
        var existingNutrition = returnNutritionIfUserHaveAccess(nutritionId, petId, userId);
        existingNutrition.setDescription(updatedNutrition.getDescription());
        existingNutrition.setBrand(updatedNutrition.getBrand());
        existingNutrition.setFoodName(updatedNutrition.getFoodName());
        nutritionRepository.save(existingNutrition);
        return NutritionMapper.INSTANCE.nutritionToDto(existingNutrition);
    }

    //fixme: find better name
    private Nutrition returnNutritionIfUserHaveAccess(final Long nutritionId, final Long petId, final Long userId) {
        petService.getPetById(petId, userId);
        var nutrition = getExistingNutritionById(nutritionId);
        if (!nutrition.getPet().getId().equals(petId)) {
            // todo: a new error class
            throw new AccessDeniedException("Pet with given id " + petId + " does not own a nutrition with id " + nutritionId);
        }
        return getExistingNutritionById(nutritionId);
    }

    private Nutrition getExistingNutritionById(final Long nutritionId) {
        return nutritionRepository.findById(nutritionId).orElseThrow(() -> new ResourceNotFoundException("Nutrition with given id does not exist"));
    }
}
