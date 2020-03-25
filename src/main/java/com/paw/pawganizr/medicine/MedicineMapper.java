package com.paw.pawganizr.medicine;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicineMapper {
    MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class);

    MedicineDto medicineToDto(Medicine medicine);

    Medicine dtoToMedicine(MedicineDto medicineDto);
}
