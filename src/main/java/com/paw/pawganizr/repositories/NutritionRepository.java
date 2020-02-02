package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, UUID> {
    @Query("select n from nutrition_details n where n.pet.id=:petId")
    List<Nutrition> findAllByPetId(@Param("petId") final UUID petId);

}
