package com.paw.pawganizr.security.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
