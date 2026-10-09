package com.example.pet.pets.domain.incoming.error;

public class PetException extends Exception {

    private final int httpStatusCode;

    public PetException(int httpStatusCode, Exception e) {
        super(e.getMessage(), e);
        this.httpStatusCode = httpStatusCode;
    }

    public PetException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
