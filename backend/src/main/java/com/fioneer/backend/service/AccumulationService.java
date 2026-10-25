package com.fioneer.backend.service;

import com.fioneer.backend.dto.AccumulationCreateDto;
import com.fioneer.backend.dto.AccumulationDto;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.Coverage;

import java.util.List;

public interface AccumulationService {

    public AccumulationDto getAccumulationByName(String name);

    public List<Coverage> getCoveragesByAccumulationName(String accumulationName);

    public AccumulationDto createAccumulation(AccumulationCreateDto accumulationCreateDto);

//    public List<Accumulation> getAll();
}
