package com.server.scarlet_shade.infra.handler;

import com.server.scarlet_shade.exception.player.slot.InvalidSlotNumberException;
import com.server.scarlet_shade.exception.player.slot.SlotNotFoundException;
import com.server.scarlet_shade.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SlotHandler {

    @ExceptionHandler(InvalidSlotNumberException.class)
    public ResponseEntity<RestErrorMessage> invalidSlotNumber(InvalidSlotNumberException e){
        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SlotNotFoundException.class)
    public ResponseEntity<RestErrorMessage> slotNotFound(SlotNotFoundException e){
        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
