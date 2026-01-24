package com.server.scarlet_shade.exception.player;

public class InvalidSlotNumber extends RuntimeException {

    public InvalidSlotNumber(){
        super("Invalid Number");
    }

    public InvalidSlotNumber(String message) {
        super(message);
    }
}
