package com.fioneer.backend.service;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;
import com.fioneer.backend.dto.CoverageUpdateDto;

public interface CoverageService {

    public CoverageDto createCoverage(CoverageCreateDto coverageCreateDto);

    public CoverageDto  updateCoverage(String name, CoverageUpdateDto coverageUpdateDto);

    public String deleteCoverage (String coverageName);
}
