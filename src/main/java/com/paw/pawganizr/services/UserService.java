package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.exceptions.UserAlreadyExistsException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.repositories.UserRepository;
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

    public AppUser createUser(final AppUser user) {
        throwIfUserWithGivenEmailExists(user.getEmail());
        return userRepository.save(user);
    }

    private Optional<AppUser> findUserById(final UUID id) {
        return userRepository.findById(id);
    }

    public AppUser findExistingUser(final UUID id) {
        return findUserById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with given id does not exist"));
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }

    public AppUser update(final UUID id, final AppUser user) {
        findExistingUser(id);
        user.setId(id);
        return userRepository.save(user);
    }

    //only for tests
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    private void throwIfUserWithGivenEmailExists(final String email) {
        userRepository.findByEmail(email)
                .ifPresent(pl -> {
                    throw new UserAlreadyExistsException("User with given email already exists");
                });
    }
}
