package com.paw.pawganizr.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Override
    Optional<AppUser> findById(Long Long);

    Boolean existsByEmail(String email);
}
