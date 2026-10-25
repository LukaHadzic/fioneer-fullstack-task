package com.fioneer.backend.service.impl;

import com.fioneer.backend.dto.AccumulationCreateDto;
import com.fioneer.backend.dto.AccumulationDto;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.ClassOfBusiness;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.entity.Status;
import com.fioneer.backend.exception.exceptionclasses.AccumulationCreationException;
import com.fioneer.backend.exception.exceptionclasses.AccumulationNotFoundException;
import com.fioneer.backend.mapper.AccumulationMapper;
import com.fioneer.backend.repository.AccumulationRepository;
import com.fioneer.backend.repository.ClassOfBusinessRepository;
import com.fioneer.backend.repository.StatusRepository;
import com.fioneer.backend.service.AccumulationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccumulationServiceImpl implements AccumulationService {

    private AccumulationRepository accumulationRepository;
    private AccumulationMapper accumulationMapper;
    private ClassOfBusinessRepository classOfBusinessRepository;
    private StatusRepository statusRepository;

    public AccumulationServiceImpl(AccumulationRepository accumulationRepository,
                                   ClassOfBusinessRepository classOfBusinessRepository,
                                   AccumulationMapper accumulationMapper, StatusRepository statusRepository) {
        this.accumulationRepository = accumulationRepository;
        this.classOfBusinessRepository = classOfBusinessRepository;
        this.accumulationMapper = accumulationMapper;
        this.statusRepository = statusRepository;
    }

    @Override
    public AccumulationDto getAccumulationByName(String name) {
        Optional<Accumulation> accumulationDb = accumulationRepository.findByName(name);

        if (accumulationDb.isEmpty()) throw new AccumulationNotFoundException();

        return accumulationMapper.toAccumulationDto(accumulationDb.get());

    }

    @Override
    public List<Coverage> getCoveragesByAccumulationName(String accumulationName) {
        Optional<Accumulation> dbAccumulation =  accumulationRepository.findByName(accumulationName);

        if (dbAccumulation.isEmpty()) return null;

        return dbAccumulation.get().getCoverages();
    }

    @Override
    public AccumulationDto createAccumulation(AccumulationCreateDto accumulationCreateDto) {

        String dtoName = accumulationCreateDto.getName();
        String dtoBusinessName = accumulationCreateDto.getBusiness();
        String dtoStatus = accumulationCreateDto.getStatus();
        String dtoDescription = accumulationCreateDto.getDescription();

        if (dtoName == null || dtoName.isEmpty() ||
        dtoBusinessName == null || dtoBusinessName.isEmpty() ||
        dtoStatus == null || dtoStatus.isEmpty()) throw new AccumulationCreationException("Accumulation's name is empty");

        Optional<Accumulation> accumulationDb = accumulationRepository.findByName(dtoName);
        if (accumulationDb.isPresent()) throw new AccumulationCreationException("Accumulation already exists");

        Optional<ClassOfBusiness> dbBusinessClass = classOfBusinessRepository.findByName(dtoBusinessName);
        if (dbBusinessClass.isEmpty()) throw new AccumulationCreationException("Class of business with provided name doesn't exist");

        Optional<Status> dbStatus = statusRepository.findByStatus(dtoStatus);
        if (dbStatus.isEmpty()) throw new AccumulationCreationException("Provided status doesn't exist");

        Accumulation newAccumulation = new Accumulation(dtoName, dbBusinessClass.get(), dbStatus.get(), dtoDescription);

        return accumulationMapper.toAccumulationDto(accumulationRepository.saveAndFlush(newAccumulation));

    }

//    @Override
//    public List<Accumulation> getAll() {
//
//        return accumulationRepository.findAll();
//    }
}
