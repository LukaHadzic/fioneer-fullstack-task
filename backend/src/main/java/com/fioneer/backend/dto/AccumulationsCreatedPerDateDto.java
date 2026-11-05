package com.fioneer.backend.dto;

import java.time.LocalDate;

public class AccumulationsCreatedPerDateDto {

    LocalDate dateOfCreation;
    Long numberCreated;

    public AccumulationsCreatedPerDateDto(LocalDate dateOfCreation, Long numberCreated) {
        this.dateOfCreation = dateOfCreation;
        this.numberCreated = numberCreated;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Long getNumberCreated() {
        return numberCreated;
    }

    public void setNumberCreated(Long numberCreated) {
        this.numberCreated = numberCreated;
    }
}
