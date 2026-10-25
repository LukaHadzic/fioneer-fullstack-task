package com.fioneer.backend.service.impl;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.ClassOfBusiness;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.mapper.CoverageMapper;
import com.fioneer.backend.repository.AccumulationRepository;
import com.fioneer.backend.repository.ClassOfBusinessRepository;
import com.fioneer.backend.repository.CoverageRepository;
import com.fioneer.backend.service.CoverageService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CoverageServiceImpl implements CoverageService {

    private CoverageRepository coverageRepository;
    private AccumulationRepository accumulationRepository;
    private ClassOfBusinessRepository classOfBusinessRepository;
    private CoverageMapper coverageMapper;

    public CoverageServiceImpl(CoverageRepository coverageRepository, AccumulationRepository accumulationRepository,
                               ClassOfBusinessRepository classOfBusinessRepository, CoverageMapper coverageMapper) {
        this.coverageRepository = coverageRepository;
        this.accumulationRepository = accumulationRepository;
        this.classOfBusinessRepository = classOfBusinessRepository;
        this.coverageMapper = coverageMapper;
    }

    @Override
    public CoverageDto createCoverage(CoverageCreateDto coverageCreateDto) {

        String dtoName = coverageCreateDto.getName();
        BigDecimal dtoSumIssured = coverageCreateDto.getSumInsured();
        String dtoBusinessName = coverageCreateDto.getBusinessName();
        String dtoAccumulationName = coverageCreateDto.getAccumulationName();

        if (dtoName == null || dtoName.isEmpty()) throw new RuntimeException("Failed to create coverage - coverage's name is empty");
        if (dtoSumIssured == null || dtoSumIssured.compareTo(BigDecimal.ZERO) <= 0) throw new RuntimeException("Failed to create coverage - coverage's sum issured not valid");

        Optional<Coverage> covDb = coverageRepository.findByName(dtoName);
        if (covDb.isPresent()) throw new RuntimeException("Failed to create coverage - coverage already exists");

        Optional<ClassOfBusiness> dbBusinessClass = classOfBusinessRepository.findByName(dtoBusinessName);
        if (dbBusinessClass.isEmpty()) throw new RuntimeException("Failed to create coverage - class of business with provided name  doesn't exist");

        Optional<Accumulation> accumDb = accumulationRepository.findByName(dtoAccumulationName);
        if (accumDb.isEmpty()) throw new RuntimeException("Failed to create coverage - accumulation doesn't exist");

        Accumulation accumDbObj  = accumDb.get();
        if (!accumDbObj.getClassOfBusiness().getName().equals(dtoBusinessName)) throw new RuntimeException("Failed to create coverage, classes of business of coverage and accumulation don't match");

        // Coverage data are valid, accumulation exists
        Coverage saveCoverage = new Coverage(dtoName, dtoSumIssured,
                accumDb.get().getClassOfBusiness(), accumDbObj);

        Coverage savedCoverage = coverageRepository.saveAndFlush(saveCoverage);

        accumDbObj.addCoverage(savedCoverage);
        accumulationRepository.save(accumDbObj);

        return coverageMapper.toCoverageDto(savedCoverage);
    }

}
