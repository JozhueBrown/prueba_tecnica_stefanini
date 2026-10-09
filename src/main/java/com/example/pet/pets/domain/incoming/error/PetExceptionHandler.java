package com.example.pet.pets.domain.incoming.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PetExceptionHandler {

    @ExceptionHandler(PetException.class)
    public ResponseEntity<String> handlePetException(PetException e) {
        return ResponseEntity
                .status(e.getHttpStatusCode())
                .body(e.getMessage());
    }
}