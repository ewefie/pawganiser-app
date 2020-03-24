package com.paw.pawganizr.medicine;

import com.paw.pawganizr.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Long;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {


    List<Medicine> findAllByPetId(final Long petId);

    void deleteAllByPetId(final Long petId);
}
