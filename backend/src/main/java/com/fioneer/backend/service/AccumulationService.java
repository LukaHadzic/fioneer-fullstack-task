package com.fioneer.backend.service;

import com.fioneer.backend.dto.*;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.Coverage;

import java.util.List;

public interface AccumulationService {

    public AccumulationDto getAccumulationByName(String name);

    public AccumulationDto getAccumulationById(Long id);

    public List<CoverageDto> getCoveragesByAccumulationName(String accumulationName);

    public AccumulationsSummaryDto getAverageSumInsuredPerStatus();

    public AccumulationDto createAccumulation(AccumulationCreateDto accumulationCreateDto);

    public List<AccumulationDto> getAll();

    public String delete(String accumulationName);
}
