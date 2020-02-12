package com.paw.pawganizr.controllers;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.security.CurrentUser;
import com.paw.pawganizr.security.UserPrincipal;
import com.paw.pawganizr.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://pawganiser.sdacademy.xyz"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public AppUser getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/")
    public AppUser getUserByPrincipalData(final Principal principal) throws ResourceNotFoundException {
        return userService.createOrUpdateUser(principal);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(final Principal principal) throws ResourceNotFoundException {
        userService.deleteByPrincipal(principal);
    }
}
