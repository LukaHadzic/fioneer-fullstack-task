package com.fioneer.backend.service;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;

public interface CoverageService {

    public CoverageDto createCoverage(CoverageCreateDto coverageCreateDto);

}
