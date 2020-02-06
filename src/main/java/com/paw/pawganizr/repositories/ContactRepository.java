package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {

    List<Contact> findAllByUser(final AppUser user);


    @Query("select c from contacts c where c.user.id = :ownerId")
    List<Contact> findAllContactsByOwnerId(@Param("ownerId") UUID ownerId);

    void deleteAllByUserId(UUID userId);


}
