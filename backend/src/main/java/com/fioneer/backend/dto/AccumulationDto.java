package com.fioneer.backend.dto;

import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.mapper.CoverageMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccumulationDto {

    private Long id;
    private String name;
    private String business;
    private String status;
    private String description;
    private BigDecimal minSumInsured;
    private List<CoverageDto> coverages;
    private Long numCoverages;

    public AccumulationDto(String name, String business, String status, String description) {
        this.name = name;
        this.business = business;
        this.status = status;
        this.description = description;
    }

    public AccumulationDto(String name, String business, String status, String description, List<Coverage> coverages) {
        this.name = name;
        this.business = business;
        this.status = status;
        this.description = description;

        this.coverages = new ArrayList<>();
        this.numCoverages = 0L;
        if (coverages != null) {
            for (Coverage coverage : coverages) {
                if (coverage != null && coverage.getSumInsured() != null) {
                    this.coverages.add(CoverageMapper.toCoverageDto(coverage));
                    if (this.minSumInsured== null) {
                        this.minSumInsured = coverage.getSumInsured();
                    } else {
                        this.minSumInsured = this.minSumInsured.min(coverage.getSumInsured());
                    }
                    this.numCoverages++;
                }
            }
        }
    }

    public AccumulationDto(Long id, String name, String business, String status, String description) {
        this.id = id;
        this.name = name;
        this.business = business;
        this.status = status;
        this.description = description;
    }

    public AccumulationDto(Long id, String name, String business, String status, String description, List<Coverage> coverages) {
        this.id = id;
        this.name = name;
        this.business = business;
        this.status = status;
        this.description = description;
        this.coverages = new ArrayList<>();
        this.numCoverages = 0L;
        if (coverages != null) {
            for (Coverage coverage : coverages) {
                if (coverage != null && coverage.getSumInsured() != null) {
                    this.coverages.add(CoverageMapper.toCoverageDto(coverage));
                    if (this.minSumInsured== null) {
                        this.minSumInsured = coverage.getSumInsured();
                    } else {
                        this.minSumInsured = this.minSumInsured.min(coverage.getSumInsured());
                    }
                    this.numCoverages++;
                }
            }
        }
    }

    public Long getNumCoverages() {
        return numCoverages;
    }

    public void setNumCoverages(Long numCoverages) {
        this.numCoverages = numCoverages;
    }

    public BigDecimal getMinSumInsured() {
        return minSumInsured;
    }

    public void setMinSumInsured(BigDecimal minSumInsured) {
        this.minSumInsured = minSumInsured;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CoverageDto> getCoverages() {
        return coverages;
    }

    public void setCoverages(List<CoverageDto> coverages) {
        this.coverages = coverages;
    }
}
