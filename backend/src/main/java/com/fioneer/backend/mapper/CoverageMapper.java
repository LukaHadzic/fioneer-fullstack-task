package com.fioneer.backend.mapper;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.entity.Coverage;

public class CoverageMapper {

    private Coverage toCoverage(CoverageCreateDto coverageCreate) {
        return coverageCreate.getCoverage();
    }

}
