package com.paw.pawganizr.repository;

import com.paw.pawganizr.model.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
}
