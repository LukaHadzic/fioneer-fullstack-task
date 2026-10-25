package com.fioneer.backend.exception.exceptionclasses;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException() {
        super("Status not found");
    }
}
