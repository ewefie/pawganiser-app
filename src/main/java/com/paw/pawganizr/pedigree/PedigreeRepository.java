package com.paw.pawganizr.pedigree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedigreeRepository extends JpaRepository<Pedigree, Long> {

    void deleteByPetId(Long petId);

    Optional<Pedigree> findByPetId(Long petId);

}
