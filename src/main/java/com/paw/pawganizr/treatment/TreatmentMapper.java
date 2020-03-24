package com.paw.pawganizr.treatment;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TreatmentMapper {
    TreatmentMapper INSTANCE = Mappers.getMapper(TreatmentMapper.class);

    TreatmentDto treatmentToDto(Treatment treatment);

    Treatment dtoToTreatment(TreatmentDto treatmentDto);
}
