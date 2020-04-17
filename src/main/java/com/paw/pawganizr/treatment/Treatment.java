package com.paw.pawganizr.treatment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@Entity(name = "treatments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "treatment_type")
    @Enumerated(EnumType.STRING)
    private TreatmentType type;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "treatment_start_date")
    private LocalDate treatmentStartDate;

    @Column(name = "treatment_end_date")
    private LocalDate treatmentEndDate;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @AssertTrue
    @JsonIgnore
    private boolean isTreatmentEndDateValid() {
        return isNull(treatmentEndDate) || !treatmentEndDate.isBefore(getTreatmentStartDate());
    }
}
