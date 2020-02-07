package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, UUID> {

    List<Treatment> findAllByPetId(final UUID petId);

    void deleteAllByPetId(UUID petId);

}
