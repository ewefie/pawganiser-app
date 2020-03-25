package com.paw.pawganizr.nutrition;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NutritionMapper {
    NutritionMapper INSTANCE = Mappers.getMapper(NutritionMapper.class);

    Nutrition dtoToNutrition(NutritionDto nutritionDto);

    NutritionDto nutritionToDto(Nutrition nutrition);

}
