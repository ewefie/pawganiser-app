package com.paw.pawganizr.service;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.repository.UserRepository;
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
        userRepository.save(user);
        return user;
    }

    public Optional<AppUser> findUserById(final UUID id) {
        return userRepository.findById(id);
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }

    public AppUser update(final UUID id, final AppUser user) {
//        todo: add method body maybe in Controller class - not here
        return null;
    }


    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
}
