package com.fioneer.backend.dto;

import com.fioneer.backend.entity.Coverage;

public class CoverageCreateDto {

    private Coverage coverage;
    private String accumulationName;

    public CoverageCreateDto(Coverage coverage, String accumulationName) {
        this.coverage = coverage;
        this.accumulationName = accumulationName;
    }

    public String getAccumulationName() {
        return accumulationName;
    }

    public void setAccumulationName(String accumulationName) {
        this.accumulationName = accumulationName;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }
}
