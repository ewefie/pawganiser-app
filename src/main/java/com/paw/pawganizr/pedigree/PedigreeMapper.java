package com.paw.pawganizr.pedigree;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedigreeMapper {
    PedigreeMapper INSTANCE = Mappers.getMapper(PedigreeMapper.class);

   PedigreeDto pedigreeToDto(Pedigree pedigree);

    Pedigree dtoToPedigree(PedigreeDto pedigreeDto);
}
