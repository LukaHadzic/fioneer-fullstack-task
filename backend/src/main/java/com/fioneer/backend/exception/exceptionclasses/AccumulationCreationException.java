package com.fioneer.backend.exception.exceptionclasses;

public class AccumulationCreationException extends RuntimeException {

    public AccumulationCreationException(String message){
        super("Failed to create Accumulation - " + message);
    }
}
