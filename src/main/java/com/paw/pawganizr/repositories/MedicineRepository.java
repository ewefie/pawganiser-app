package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.Medicine;
import com.paw.pawganizr.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {


    List<Medicine> findAllByPetId(final UUID petId);

    void deleteAllByPetId(final UUID petId);
}
