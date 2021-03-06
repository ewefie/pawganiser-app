package com.paw.pawganizr.security.user;

import com.paw.pawganizr.security.jwt.AuthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(value = "Authentication and authorization")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Log in", response = AuthResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in"),
            @ApiResponse(code = 400, message = "Bad credentials"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public AuthResponse login(@Valid @RequestBody final LoginRequest loginRequest) {
        return new AuthResponse(authService.authenticateUser(loginRequest));
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Sign up", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully signed up"),
            @ApiResponse(code = 400, message = "Bad credentials"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 409, message = "User with given email already exist")
    })
    public com.paw.pawganizr.security.user.ApiResponse signup(@Valid @RequestBody final SignUpRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return new com.paw.pawganizr.security.user.ApiResponse(true, "User registered successfully");
    }
}
