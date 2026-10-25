package com.fioneer.backend.exception.exceptionclasses;

public class AccumulationNotFoundException extends RuntimeException {

    public AccumulationNotFoundException() {
        super("Accumulation not found");
    }
}
