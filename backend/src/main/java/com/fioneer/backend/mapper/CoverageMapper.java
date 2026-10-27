package com.fioneer.backend.mapper;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;
import com.fioneer.backend.entity.Coverage;
import org.springframework.stereotype.Component;

@Component
public class CoverageMapper {
//
//    private Coverage toCoverage(CoverageCreateDto coverageCreate) {
//        return coverageCreate.getCoverage();
//    }


    public CoverageMapper() {
    }

    public static CoverageDto toCoverageDto(Coverage coverage){
        return new CoverageDto(coverage.getName(), coverage.getSumInsured(),
                coverage.getClassOfBusiness().getName(), coverage.getAccumulation().getName());
    }

}
