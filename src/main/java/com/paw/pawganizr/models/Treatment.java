package com.paw.pawganizr.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity(name = "treatments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

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

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @AssertTrue
    @JsonIgnore
    private boolean isTreatmentEndDateValid() {
        return isNull(treatmentEndDate) || treatmentEndDate.isAfter(treatmentStartDate);
    }
}
