package com.fioneer.backend.dto;

import java.math.BigDecimal;

public class AverageSumInsuredPerStatusDto {

    private String status;
    private Double averageInsured;

    public AverageSumInsuredPerStatusDto(String status, Number averageInsured) {
        this.status = status;
        this.averageInsured = (Double) averageInsured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAverageInsured() {
        return averageInsured;
    }

    public void setAverageInsured(Double averageInsured) {
        this.averageInsured = averageInsured;
    }
}
