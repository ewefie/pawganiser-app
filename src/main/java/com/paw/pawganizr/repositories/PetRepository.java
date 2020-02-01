package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    List<Pet> findAllByOwner(AppUser owner);
}
