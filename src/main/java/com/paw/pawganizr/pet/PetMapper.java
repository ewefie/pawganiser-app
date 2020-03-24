package com.paw.pawganizr.pet;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PetMapper {
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    Pet BasicPetInfoToPet(BasicPetInfo petInfo);

    BasicPetInfo petToBasicPetInfo(Pet pet);

    PetDto petToPetDto(Pet pet);

    Pet petDtoToPet(PetDto petDto);
}
