package com.paw.pawganizr.security.user;

import com.paw.pawganizr.security.jwt.TokenProvider;
import com.paw.pawganizr.user.AppUser;
import com.paw.pawganizr.user.AppUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(final AuthenticationManager authenticationManager, final AppUserService appUserService, final PasswordEncoder passwordEncoder, final TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public String authenticateUser(final LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }

    public AppUser registerUser(final SignUpRequest signUpRequest) {
        if (appUserService.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email address already in use.");
        }
        var user = SignupRequestMapper.INSTANCE.map(signUpRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserService.saveUser(user);
    }
}
