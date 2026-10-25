package com.fioneer.backend.exception.exceptionclasses;

public class CoverageUpdateFailedException extends RuntimeException {

    public CoverageUpdateFailedException(String message) {
        super("Failed to update coverage - " + message);
    }
}
