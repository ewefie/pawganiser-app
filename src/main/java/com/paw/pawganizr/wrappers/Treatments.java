package com.paw.pawganizr.wrappers;

import com.paw.pawganizr.models.Treatment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Treatments {
    private List<Treatment> treatments;
}
