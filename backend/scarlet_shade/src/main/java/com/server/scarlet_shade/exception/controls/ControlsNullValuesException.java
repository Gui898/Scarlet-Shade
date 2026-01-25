package com.server.scarlet_shade.exception.controls;

public class ControlsNullValuesException extends RuntimeException {

    public ControlsNullValuesException() {
        super("Some value are null");
    }

    public ControlsNullValuesException(String message) {
        super(message);
    }
}
