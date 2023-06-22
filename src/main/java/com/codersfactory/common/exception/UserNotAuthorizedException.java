package com.codersfactory.common.exception;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(Long userId) {
        super("User with id '" + userId + "' is not authorized to perform this action");
    }
}
