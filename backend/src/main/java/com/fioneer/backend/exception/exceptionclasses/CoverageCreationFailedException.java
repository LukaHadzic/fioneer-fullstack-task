package com.fioneer.backend.exception.exceptionclasses;

public class CoverageCreationFailedException extends RuntimeException {

    public CoverageCreationFailedException(String message) {
        super("Failed to create coverage - " + message);
    }
}
