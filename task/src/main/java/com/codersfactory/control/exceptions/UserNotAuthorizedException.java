package com.codersfactory.control.exceptions;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(Long userId) {
        super("User with id '" + userId + "' is not authorized to perform this action");
    }
}
