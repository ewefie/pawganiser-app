package com.paw.pawganizr.controllers;

import com.paw.pawganizr.services.UserService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class HelloWorldController {

    private final UserService userService;

    public HelloWorldController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String printHello(Principal principal) {
        final Map<String, Object> attributes = ((OAuth2AuthenticationToken) principal).getPrincipal().getAttributes();
        final String fullName = (String) attributes.get("name");
        final String email = (String) attributes.get("email");
//        return userService.createOrUpdateUser(principal).toString();
        return principal.toString();
    }
}
