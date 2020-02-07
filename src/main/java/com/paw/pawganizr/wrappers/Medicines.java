package com.paw.pawganizr.wrappers;

import com.paw.pawganizr.models.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Medicines {
    private List<Medicine> medicines;
}
