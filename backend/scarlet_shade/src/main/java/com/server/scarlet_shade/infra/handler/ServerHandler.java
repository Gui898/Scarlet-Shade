package com.server.scarlet_shade.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.server.scarlet_shade.exception.ServerException;
import com.server.scarlet_shade.infra.RestErrorMessage;

@RestControllerAdvice
public class ServerHandler {
    
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<RestErrorMessage> serverError(ServerException e) {

        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}