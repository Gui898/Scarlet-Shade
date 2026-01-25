package com.server.scarlet_shade.infra.handler;

import com.server.scarlet_shade.exception.player.InvalidSlotNumber;
import com.server.scarlet_shade.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SlotHandler {

    @ExceptionHandler(InvalidSlotNumber.class)
    public ResponseEntity<RestErrorMessage> invalidSlotNumber(InvalidSlotNumber e){
        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
