package com.server.scarlet_shade.exception.player.slot;

public class InvalidSlotNumberException extends RuntimeException {

    public InvalidSlotNumberException(){
        super("Invalid Number");
    }

    public InvalidSlotNumberException(String message) {
        super(message);
    }
}
