package com.paw.pawganizr.treatment;

import com.paw.pawganizr.treatment.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Long;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findAllByPetId(final Long petId);

    void deleteAllByPetId(Long petId);

}
