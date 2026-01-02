package com.server.scarlet_shade.exception;

public class UserUnauthorizedException extends RuntimeException {

    public UserUnauthorizedException(){
        super("User not authorized");
    }

    public UserUnauthorizedException(String message) {
        super(message);
    }
}
