package com.paw.pawganizr.treatment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findAllByPetId(final Long petId);

    void deleteAllByPetId(Long petId);

}
