package com.paw.pawganizr.nutrition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Long;

@Entity(name = "nutrition_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Length(min = 2)
    @Column(name = "food_name")
    private String foodName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
