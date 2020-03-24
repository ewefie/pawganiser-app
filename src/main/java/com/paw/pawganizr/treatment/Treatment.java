package com.paw.pawganizr.treatment;


import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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
    private TreatmentType type;

    @Column(name = "description")
    private String description;

    @Column(name = "treatment_start_date")
    private LocalDate treatmentStartDate;

    @Column(name = "treatment_end_date")
    private LocalDate treatmentEndDate;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
