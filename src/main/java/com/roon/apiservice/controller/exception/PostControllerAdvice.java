package com.roon.apiservice.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostControllerAdvice {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgException(IllegalArgumentException illegalArgumentException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(illegalArgumentException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exception(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
