package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identyfikator jest generowany przez bazę
    private Long id;
    private String name;
    private MedicineType type;
    private String dosage;
    private LocalDate treatmentStartDate;
    private LocalDate treatmentEndDate;
}
