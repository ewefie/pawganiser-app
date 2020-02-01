package com.paw.pawganizr.controllers;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.services.UserService;
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
    public AppUser createNewUser(@Valid @RequestBody AppUser user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        AppUser appUser = userService.findExistingUser(id);
        return ResponseEntity.ok().body(appUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable(value = "id") UUID id,
                                              @Valid @RequestBody AppUser userDetails) throws ResourceNotFoundException {
        AppUser appUser = userService
                .findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet user not found for this id :: " + id));
        //fixme: move it to method
        appUser.setEmail(userDetails.getEmail());
        appUser.setFirstName(userDetails.getFirstName());
        appUser.setLastName(userDetails.getLastName());

        final AppUser updatedUser = userService.save(appUser);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
