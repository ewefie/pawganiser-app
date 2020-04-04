package com.paw.pawganizr.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
//    @Query("SELECT c FROM contacts c  WHERE c.user.id=?1")
    List<Contact> findAllByUserId(final Long userId);

    void deleteAllByUserId(Long userId);
}
