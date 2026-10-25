package com.fioneer.backend.exception.exceptionclasses;

public class StatusAlreadyExists extends RuntimeException{

    public StatusAlreadyExists(){
        super("Status already exists");
    }
}
