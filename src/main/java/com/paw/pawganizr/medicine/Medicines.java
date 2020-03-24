package com.paw.pawganizr.medicine;

import com.paw.pawganizr.medicine.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Medicines {
    private List<Medicine> medicines;
}
