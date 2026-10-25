package com.fioneer.backend.controller;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.service.CoverageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coverages")
public class CoverageController {

    private CoverageService coverageService;

    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }

    @PostMapping("/create")
    public CoverageDto createCoverage(@Valid @RequestBody CoverageCreateDto createCoverageDto) {

        return coverageService.createCoverage(createCoverageDto);
    }


}
