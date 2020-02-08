package com.paw.pawganizr.controllers;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createNewUser(@Valid @RequestBody final AppUser user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable(value = "id") final UUID id) throws ResourceNotFoundException {
        return userService.findExistingUser(id);
    }

    //only for tests
    @GetMapping("/")
    public List<AppUser> getAllUsers() throws ResourceNotFoundException {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable(value = "id") final UUID id,
                           @Valid @RequestBody AppUser userDetails) throws ResourceNotFoundException {
        userService.update(id, userDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id") final UUID id) throws ResourceNotFoundException {
        userService.delete(id);
    }
}
