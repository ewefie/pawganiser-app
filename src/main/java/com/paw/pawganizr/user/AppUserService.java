package com.paw.pawganizr.user;


import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    public AppUser saveUser(final AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUserDto getUserDtoByAppUserId(final Long userId) {
        var user = getExistingUser(userId);
        return AppUserMapper.INSTANCE.map(user);
    }

    public void deleteUser(final Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser getExistingUser(final Long id) {
        return getOptionalUser(id).orElseThrow(() ->
                new ResourceNotFoundException("User with given id does not exist"));
    }

    private Optional<AppUser> getOptionalUser(final Long id) {
        return appUserRepository.findById(id);
    }

    public boolean existsByEmail(final String email) {
        return appUserRepository.existsByEmail(email);
    }
}
