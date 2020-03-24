package com.paw.pawganizr.treatment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.pet.Pet;
import com.paw.pawganizr.treatment.TreatmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Long;

import static java.util.Objects.isNull;

@Entity(name = "treatments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(generator = "Long")
    @GenericGenerator(
            name = "Long",
            strategy = "org.hibernate.id.LongGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "treatment_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TreatmentType type;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "treatment_start_date")
    private LocalDateTime treatmentStartDate;

    @Column(name = "treatment_end_date")
    private LocalDateTime treatmentEndDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @AssertTrue
    @JsonIgnore
    private boolean isTreatmentEndDateValid() {
        return isNull(treatmentEndDate) || !treatmentEndDate.isBefore(getTreatmentStartDate());
    }
}
