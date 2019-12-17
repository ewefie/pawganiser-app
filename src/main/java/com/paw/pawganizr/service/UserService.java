package com.paw.pawganizr.service;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public AppUser save(final AppUser user) {
        userRepository.save(user);
        return user;
    }

    public Optional<AppUser> findUserById(final Long id) {
        return userRepository.findById(id);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    public AppUser update(final Long id, final AppUser user) {
//        todo: add method body maybe in Controller class - not here
        return null;
    }


    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
}
