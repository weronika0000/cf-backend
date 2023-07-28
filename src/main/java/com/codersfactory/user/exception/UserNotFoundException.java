package com.codersfactory.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User with provided email not found");
    }
}
