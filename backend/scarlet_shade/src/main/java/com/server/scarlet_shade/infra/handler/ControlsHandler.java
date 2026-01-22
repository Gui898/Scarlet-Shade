package com.server.scarlet_shade.infra.handler;

import com.server.scarlet_shade.exception.controls.ControlsNotFound;
import com.server.scarlet_shade.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControlsHandler {

    @ExceptionHandler(ControlsNotFound.class)
    public ResponseEntity<RestErrorMessage> controlsNotFound(ControlsNotFound e){
        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
