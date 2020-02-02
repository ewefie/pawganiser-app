package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    List<Pet> findAllByOwner(AppUser owner);

    @Query("select p from pets  p where  p.owner.id = :ownerId")
    List<Pet> findAllPetsByOwnerId(@Param("ownerId") UUID ownerId);
}
