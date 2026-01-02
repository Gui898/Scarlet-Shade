package com.server.scarlet_shade.infra;

import com.server.scarlet_shade.exception.user.UserConflictException;
import com.server.scarlet_shade.exception.user.UserInvalidValuesException;
import com.server.scarlet_shade.exception.user.UserNotFoundException;
import com.server.scarlet_shade.exception.user.UserUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException e){
        RestErrorMessage message = new RestErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserConflictException.class)
    private ResponseEntity<RestErrorMessage> userConflictHandler(UserConflictException e){
        RestErrorMessage message = new RestErrorMessage(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserInvalidValuesException.class)
    private ResponseEntity<RestErrorMessage> userInvalidValuesHandler(UserInvalidValuesException e){
        RestErrorMessage message = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    private ResponseEntity<RestErrorMessage> userUnauthorizedHandler(UserUnauthorizedException e){
        RestErrorMessage message = new RestErrorMessage(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }


}
