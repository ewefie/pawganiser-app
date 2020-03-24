package com.paw.pawganizr.pet;

import com.paw.pawganizr.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByOwner(AppUser owner);

    List<Pet> findAllByOwnerId(Long ownerId);

    List<Pet> findAllByOwner_Id(Long ownerId);

    void deleteAllByOwnerId(final Long ownerId);
}
