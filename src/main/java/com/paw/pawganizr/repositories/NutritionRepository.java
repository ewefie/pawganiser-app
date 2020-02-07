package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, UUID> {

    List<Nutrition> findAllByPetId(final UUID petId);

    void deleteAllByPetId(final UUID petId);
}
