package com.paw.pawganizr.treatment;

import com.paw.pawganizr.treatment.Treatment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Treatments {
    private List<Treatment> treatments;
}
