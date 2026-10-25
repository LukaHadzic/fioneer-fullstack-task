package com.fioneer.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CoverageCreateDto {

    @NotBlank
    private String name;
    @NotNull
    private BigDecimal sumInsured;
    @NotBlank
    private String businessName;
    @NotBlank
    private String accumulationName;

    public CoverageCreateDto() {
    }

    public CoverageCreateDto(String name, BigDecimal sumInsured, String businessName, String accumulationName) {
        this.name = name;
        this.sumInsured = sumInsured;
        this.businessName = businessName;
        this.accumulationName = accumulationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAccumulationName() {
        return accumulationName;
    }

    public void setAccumulationName(String accumulationName) {
        this.accumulationName = accumulationName;
    }
}
