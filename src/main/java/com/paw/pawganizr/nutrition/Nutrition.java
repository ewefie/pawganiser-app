package com.paw.pawganizr.nutrition;

import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity(name = "nutrition_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

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
