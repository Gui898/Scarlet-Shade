package com.server.scarlet_shade.exception.controls;

public class ControlsNotFoundException extends RuntimeException {

    public ControlsNotFoundException(){
        super("Controls were not found!");
    }

    public ControlsNotFoundException(String message) {
        super(message);
    }
}
