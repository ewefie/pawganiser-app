package com.paw.pawganizr.repositories;

import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {

    List<Contact> findAllByUser(final AppUser user);
}
