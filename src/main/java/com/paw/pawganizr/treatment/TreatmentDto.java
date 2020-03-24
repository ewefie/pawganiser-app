package com.paw.pawganizr.treatment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentDto {
    private Long id;
    @NotNull
    private TreatmentType type;
    private String description;
    @NotNull
    private LocalDate treatmentStartDate;
    private LocalDate treatmentEndDate;

    @AssertTrue
    @JsonIgnore
    private boolean isTreatmentEndDateValid() {
        return isNull(treatmentEndDate) || !treatmentEndDate.isBefore(getTreatmentStartDate());
    }
}
