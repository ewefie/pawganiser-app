package com.paw.pawganizr.nutrition;

import com.paw.pawganizr.nutrition.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Long;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {

    List<Nutrition> findAllByPetId(final Long petId);

    void deleteAllByPetId(final Long petId);
}
