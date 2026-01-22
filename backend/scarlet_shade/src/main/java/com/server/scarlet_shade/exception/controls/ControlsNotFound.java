package com.server.scarlet_shade.exception.controls;

public class ControlsNotFound extends RuntimeException {

    public ControlsNotFound(){
        super("Controls were not found!");
    }

    public ControlsNotFound(String message) {
        super(message);
    }
}
