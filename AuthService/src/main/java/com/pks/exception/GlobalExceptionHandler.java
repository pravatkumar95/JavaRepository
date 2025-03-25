package com.pks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<String> existUserNameException(UserAlreadyExistsException exception){

        String message=exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);


    }
}
