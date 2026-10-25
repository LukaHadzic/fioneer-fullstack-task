package com.fioneer.backend.dto;

import com.fioneer.backend.entity.Coverage;

import java.util.List;

public class AccumulationDto {

    private String name;
    private String business;
    private String status;
    private String description;
    private List<Coverage> coverages;

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
        this.coverages = coverages;
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
}
