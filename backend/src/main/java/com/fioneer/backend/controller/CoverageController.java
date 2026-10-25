package com.fioneer.backend.controller;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;
import com.fioneer.backend.dto.CoverageUpdateDto;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.service.CoverageService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coverages")
public class CoverageController {

    private CoverageService coverageService;

    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }

    @PostMapping("/create")
    public ResponseEntity<CoverageDto> createCoverage(@Valid @RequestBody CoverageCreateDto createCoverageDto) {

        return new ResponseEntity<>(coverageService.createCoverage(createCoverageDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/name")
    public ResponseEntity<CoverageDto> updateCoverage(@PathVariable String name, @Valid @RequestBody CoverageUpdateDto updateCoverageDto) {

        return new ResponseEntity<>(coverageService.updateCoverage(name, updateCoverageDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCoverage(String coverageName) {

        return new ResponseEntity<>(coverageService.deleteCoverage(coverageName), HttpStatus.OK);
    }

}
