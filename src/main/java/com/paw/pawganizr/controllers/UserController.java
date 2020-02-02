package com.paw.pawganizr.controllers;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "adres na którym będzie zdeployowany angular")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createNewUser(@Valid @RequestBody AppUser user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return userService.findExistingUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable(value = "id") UUID id,
                           @Valid @RequestBody AppUser userDetails) throws ResourceNotFoundException {
        userService.update(id, userDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        userService.delete(id);
    }

    //todo: method to update partially
}
