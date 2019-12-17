package com.paw.pawganizr.controller;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "adres na którym będzie zdeployowany angular")
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public List<AppUser> getAllUSers() {
        return userService.findAll();
    }

    @PostMapping("/")
    public AppUser createNewUser(@Valid @RequestBody AppUser user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        AppUser appUser = userService
                .findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet user not found for this id :: " + id));
        return ResponseEntity.ok().body(appUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable(value = "id") Long id,
                                              @Valid @RequestBody AppUser userDetails) throws ResourceNotFoundException {
        AppUser appUser = userService
                .findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet user not found for this id :: " + id));
        //fixme: move it to method
        appUser.setEmail(userDetails.getEmail());
        appUser.setFirstName(userDetails.getFirstName());
        appUser.setLastName(userDetails.getLastName());
        appUser.setFavouriteVet(userDetails.getFavouriteVet());

//        rather not this way
//        appUser.setPets(userDetails.getPets());

        final AppUser updatedUser = userService.save(appUser);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        AppUser user = userService
                .findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet user not found for this id :: " + id));
        userService.delete(user.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
