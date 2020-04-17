package com.paw.pawganizr.pet;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PetMapper {
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetDto petToDto(Pet pet);

    Pet dtoToPet(PetDto petDto);
}
