package com.paw.pawganizr.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Medicines {
    private List<MedicineDto> medicines;
}
