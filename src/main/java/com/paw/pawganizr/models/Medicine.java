package com.paw.pawganizr.models;

import com.paw.pawganizr.enums.MedicineImportancy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "medicine_name")
    @NotNull
    @Length(min = 2)
    private String name;

//    private MedicineType type;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "importancy")
    @Enumerated(EnumType.STRING)
    private MedicineImportancy importancy;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    /**
     * optional
     */
//    private LocalDate treatmentStartDate;
//    private LocalDate treatmentEndDate;
}
