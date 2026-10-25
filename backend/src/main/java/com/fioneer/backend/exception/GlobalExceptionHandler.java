package com.fioneer.backend.exception;

import com.fioneer.backend.exception.exceptionclasses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccumulationCreationException.class)
    public ResponseEntity<String> jwtInvalidToken(AccumulationCreationException accumulationCreationException) {

        return new ResponseEntity<>(accumulationCreationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccumulationNotFoundException.class)
    public ResponseEntity<String> jwtInvalidToken(AccumulationNotFoundException accumulationNotFoundException) {

        return new ResponseEntity<>(accumulationNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassOfBusinessAlreadyExistsException.class)
    public ResponseEntity<String> jwtInvalidToken(ClassOfBusinessAlreadyExistsException classOfBusinessAlreadyExistsException) {

        return new ResponseEntity<>(classOfBusinessAlreadyExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassOfBusinessNotFoundException.class)
    public ResponseEntity<String> jwtInvalidToken(ClassOfBusinessNotFoundException classOfBusinessNotFoundException) {

        return new ResponseEntity<>(classOfBusinessNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoverageCreationFailedException.class)
    public ResponseEntity<String> jwtInvalidToken(CoverageCreationFailedException coverageCreationFailedException) {

        return new ResponseEntity<>(coverageCreationFailedException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoverageUpdateFailedException.class)
    public ResponseEntity<String> jwtInvalidToken(CoverageUpdateFailedException coverageUpdateFailedException) {

        return new ResponseEntity<>(coverageUpdateFailedException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StatusAlreadyExists.class)
    public ResponseEntity<String> jwtInvalidToken(StatusAlreadyExists statusAlreadyExists) {

        return new ResponseEntity<>(statusAlreadyExists.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<String> jwtInvalidToken(StatusNotFoundException statusNotFoundException) {

        return new ResponseEntity<>(statusNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
