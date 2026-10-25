package com.fioneer.backend.dto;

import com.fioneer.backend.entity.ClassOfBusiness;
import jakarta.validation.constraints.NotNull;

public class AccumulationCreateDto {

    @NotNull
    private String name;
    @NotNull
    private String business;
    @NotNull
    private String status;
    private String description;

    public AccumulationCreateDto(String name, String business, String status, String description) {
        this.name = name;
        this.business = business;
        this.status = status;
        if (description == null || description.isEmpty()) {
            this.description = "";
        } else {
            this.description = description;
        }
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
