package com.fioneer.backend.service.impl;

import com.fioneer.backend.dto.CoverageCreateDto;
import com.fioneer.backend.dto.CoverageDto;
import com.fioneer.backend.dto.CoverageUpdateDto;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.ClassOfBusiness;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.exception.exceptionclasses.CoverageCreationFailedException;
import com.fioneer.backend.exception.exceptionclasses.CoverageUpdateFailedException;
import com.fioneer.backend.mapper.CoverageMapper;
import com.fioneer.backend.repository.AccumulationRepository;
import com.fioneer.backend.repository.ClassOfBusinessRepository;
import com.fioneer.backend.repository.CoverageRepository;
import com.fioneer.backend.service.CoverageService;
import org.springframework.dao.DataAccessException;
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

        if (dtoName == null || dtoName.isEmpty()) throw new CoverageCreationFailedException("Coverage's name is empty");
        if (dtoSumIssured == null || dtoSumIssured.compareTo(BigDecimal.ZERO) <= 0) throw new CoverageCreationFailedException("Failed to create coverage - coverage's sum issured not valid");

        Optional<Coverage> covDb = coverageRepository.findByName(dtoName);
        if (covDb.isPresent()) throw new CoverageCreationFailedException("Coverage already exists");

        Optional<ClassOfBusiness> dbBusinessClass = classOfBusinessRepository.findByName(dtoBusinessName);
        if (dbBusinessClass.isEmpty()) throw new CoverageCreationFailedException("Class of business with provided name doesn't exist");

        Optional<Accumulation> accumDb = accumulationRepository.findByName(dtoAccumulationName);
        if (accumDb.isEmpty()) throw new CoverageCreationFailedException("Accumulation doesn't exist");

        Accumulation accumDbObj  = accumDb.get();
        if (!accumDbObj.getClassOfBusiness().getName().equals(dtoBusinessName)) throw new CoverageUpdateFailedException("Classes of business of coverage and accumulation don't match");

        // Coverage data are valid, accumulation exists
        Coverage saveCoverage = new Coverage(dtoName, dtoSumIssured,
                accumDb.get().getClassOfBusiness(), accumDbObj);

        Coverage savedCoverage = coverageRepository.saveAndFlush(saveCoverage);

        accumDbObj.addCoverage(savedCoverage);
        accumulationRepository.save(accumDbObj);

        return coverageMapper.toCoverageDto(savedCoverage);
    }

    @Override
    public CoverageDto updateCoverage(String name, CoverageUpdateDto coverageUpdateDto) {
        String dtoName = coverageUpdateDto.getName();
        BigDecimal dtoSumInsured = coverageUpdateDto.getSumInsured();
        String dtoBusinessName = coverageUpdateDto.getBusinessName();
        String dtoAccumulationName = coverageUpdateDto.getAccumulationName();

        Optional<Coverage> covDb = coverageRepository.findByName(name);

        if (covDb.isEmpty()) throw new CoverageUpdateFailedException("No coverage found");

        Coverage covDb1 = covDb.get();

        if (dtoName != null && !dtoName.isEmpty()) {
            covDb1.setName(dtoName);
        }
        if (dtoSumInsured != null && dtoSumInsured.compareTo(BigDecimal.ZERO) > 0) {
            covDb1.setSumInsured(dtoSumInsured);
        }

        if (dtoBusinessName != null && !dtoBusinessName.isEmpty() && dtoAccumulationName != null && !dtoAccumulationName.isEmpty()) {
            Optional<Accumulation> accumDb = accumulationRepository.findByName(dtoAccumulationName);
            if (accumDb.isEmpty())
                throw new CoverageUpdateFailedException("Problem with accumulation and class of business");

            Accumulation accumDbObj = accumDb.get();

            if (!accumDbObj.getClassOfBusiness().getName().equals(dtoBusinessName))
                throw new CoverageUpdateFailedException("Problem with accumulation and class of business");

            if (!covDb1.getAccumulation().getName().equals(dtoAccumulationName)) {
                covDb1.getAccumulation().removeCoverage(covDb1);
                accumDbObj.addCoverage(covDb1);
            }

        }

        return coverageMapper.toCoverageDto(covDb1);

    }

    @Override
    public String deleteCoverage(String coverageName) {
        if (coverageName == null || coverageName.isEmpty()) return "Delete failed, invalid coverage name.";

        Optional<Coverage> covDb = coverageRepository.findByName(coverageName);
        if (covDb.isEmpty()) return "Delete failed, no coverage with provided name";

        Optional<Accumulation> accDb = accumulationRepository.findByName(covDb.get().getName());
        if (accDb.isEmpty()) return "Delete failed, no accumulation found";

        try {
            coverageRepository.delete(covDb.get());

            accDb.get().removeCoverage(covDb.get());

            return "Delete successful";

        } catch (DataAccessException e) {
            return "Delete failed due to database error.";
        }

    }


}
