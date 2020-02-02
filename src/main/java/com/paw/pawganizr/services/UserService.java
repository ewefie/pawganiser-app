package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.repositories.UserRepository;
import com.paw.pawganizr.wrappers.Pets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser save(final AppUser user) {
        return userRepository.save(user);
    }

    public Optional<AppUser> findUserById(final UUID id) {
        return userRepository.findById(id);
    }

    public AppUser findExistingUser(final UUID id) {
        return findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User with given id does not exist"));
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }

    public AppUser update(final UUID id, final AppUser user) {
        findExistingUser(id);
        user.setId(id);
        return userRepository.save(user);
    }
//todo: add update partially, validate updated user data
}
