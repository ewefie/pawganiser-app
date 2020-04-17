package com.paw.pawganizr.treatment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Treatments {
    private List<TreatmentDto> treatments;
}
