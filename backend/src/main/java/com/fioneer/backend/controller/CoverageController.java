package com.fioneer.backend.controller;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.service.CoverageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/covberages")
public class CoverageController {

    private CoverageService coverageService;

    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }

    @PostMapping("/create")
    public String createCoverage(@Valid @RequestBody CoverageCreateDto createCoverageDto) {

        return coverageService.createCoverage(createCoverageDto);
    }


}
