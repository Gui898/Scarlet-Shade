package com.server.scarlet_shade.exception.user;

public class UserInvalidValuesException extends RuntimeException {

    public UserInvalidValuesException() {
        super("Invalid values or empty fields");
    }

    public UserInvalidValuesException(String message) {
        super(message);
    }
}
