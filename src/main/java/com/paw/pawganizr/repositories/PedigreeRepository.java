package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.Pedigree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedigreeRepository extends JpaRepository<Pedigree, UUID> {

    Pedigree deleteByPetId(UUID petId);

    Pedigree findByPetId(UUID petId);


}