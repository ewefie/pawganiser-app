package com.paw.pawganizr.pedigree;

import com.paw.pawganizr.pedigree.Pedigree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Long;

@Repository
public interface PedigreeRepository extends JpaRepository<Pedigree, Long> {

    void deleteAllByPetId(Long petId);

    void deleteByPetId(Long petId);

    Optional<Pedigree> findByPetId(Long petId);

}
