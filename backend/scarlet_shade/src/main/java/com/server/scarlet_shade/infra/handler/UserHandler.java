package com.server.scarlet_shade.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.server.scarlet_shade.exception.user.UserNotFoundException;
import com.server.scarlet_shade.exception.user.UserConflictException;

import com.server.scarlet_shade.infra.RestErrorMessage;

@RestControllerAdvice
public class UserHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException e){
        
        RestErrorMessage message = new RestErrorMessage(
            HttpStatus.NOT_FOUND.value(), 
            HttpStatus.NOT_FOUND, 
            e.getMessage());
            
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserConflictException.class)
    public ResponseEntity<RestErrorMessage> userConflictHandler(UserConflictException e) {

        RestErrorMessage message = new RestErrorMessage(
            HttpStatus.CONFLICT.value(), 
            HttpStatus.CONFLICT, 
            e.getMessage());
            
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}