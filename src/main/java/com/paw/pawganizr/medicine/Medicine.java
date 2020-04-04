package com.paw.pawganizr.medicine;

import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "medicine_name")
    @Length(min = 2)
    @NotNull
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
}
