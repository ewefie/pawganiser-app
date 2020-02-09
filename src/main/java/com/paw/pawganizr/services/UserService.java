package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.exceptions.UserAlreadyExistsException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.repositories.UserRepository;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Map;
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

    public AppUser createOrUpdateUser(final Principal principal) {
        final AppUser user = principalToAppUser(principal);
        final Optional<AppUser> optionalUser = userRepository.findByEmail(user.getEmail());
        return optionalUser.orElseGet(() -> userRepository.save(user));
    }

    private AppUser principalToAppUser(final Principal principal) {
        final Map<String, Object> attributes = ((OAuth2AuthenticationToken) principal).getPrincipal().getAttributes();
        return AppUser.builder()
                .lastName((String) attributes.get("family_name"))
                .firstName((String) attributes.get("given_name"))
                .email((String) attributes.get("email"))
                .build();
    }

    public UUID getUserId(final Principal principal) {
        final Map<String, Object> attributes = ((OAuth2AuthenticationToken) principal).getPrincipal().getAttributes();
        final String email = (String) attributes.get("email");
        final Optional<AppUser> optionalAppUser = userRepository.findByEmail(email);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get().getId();
        }
        throw new ResourceNotFoundException();
    }


    private Optional<AppUser> findUserById(final UUID id) {
        return userRepository.findById(id);
    }

    public AppUser findExistingUser(final UUID id) {
        return findUserById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with given id does not exist"));
    }

    public void deleteByPrincipal(final Principal principal) {
        final UUID id = getUserId(principal);
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
