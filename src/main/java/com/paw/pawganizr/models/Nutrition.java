package com.paw.pawganizr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "nutrition_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Length(min = 2)
    @Column(name = "food_name")
    private String foodName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
