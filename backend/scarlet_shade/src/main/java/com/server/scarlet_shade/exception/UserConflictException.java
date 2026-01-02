package com.server.scarlet_shade.exception;

public class UserConflictException extends RuntimeException {

    public UserConflictException(){
        super("This user already exists");
    }

    public UserConflictException(String message) {
        super(message);
    }
}
