package com.fioneer.backend.service.impl;

import com.fioneer.backend.entity.ClassOfBusiness;
import com.fioneer.backend.exception.exceptionclasses.ClassOfBusinessAlreadyExistsException;
import com.fioneer.backend.exception.exceptionclasses.ClassOfBusinessNotFoundException;
import com.fioneer.backend.repository.ClassOfBusinessRepository;
import com.fioneer.backend.service.ClassOfBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClassOfBusinessServiceImpl implements ClassOfBusinessService {

    private ClassOfBusinessRepository classOfBusinessRepository;

    public ClassOfBusinessServiceImpl(ClassOfBusinessRepository classOfBusinessRepository) {
        this.classOfBusinessRepository = classOfBusinessRepository;
    }

    @Override
    public ClassOfBusiness getClassOfBusiness(String name) {

        Optional<ClassOfBusiness> dbClassOfBusiness = classOfBusinessRepository.findByName(name);

        if(dbClassOfBusiness.isEmpty()) throw new ClassOfBusinessNotFoundException("No such class of business");

        return dbClassOfBusiness.get();
    }

    @Override
    public ClassOfBusiness createClassOfBusiness(String name) {
        Optional<ClassOfBusiness> dbClassOfBusiness = classOfBusinessRepository.findByName(name);

        if(dbClassOfBusiness.isPresent()) throw new ClassOfBusinessAlreadyExistsException("Class of business already exists");

        ClassOfBusiness savedClassOfBusiness = classOfBusinessRepository.saveAndFlush(new ClassOfBusiness(name));

        return savedClassOfBusiness;

    }

    @Override
    public ClassOfBusiness updateClassOfBusiness(String oldName, String newName) {

        Optional<ClassOfBusiness> dbClassOfBusiness = classOfBusinessRepository.findByName(oldName);

        if(dbClassOfBusiness.isEmpty()) throw new ClassOfBusinessNotFoundException("No such class of business");

        ClassOfBusiness dbClassOfBusinessTemp = dbClassOfBusiness.get();

        dbClassOfBusinessTemp.setName(newName);

        return classOfBusinessRepository.saveAndFlush(dbClassOfBusinessTemp);

    }

    @Override
    @Transactional
    public String deleteClassOfBusiness(String name) {

        Optional<ClassOfBusiness> dbClassOfBusiness = classOfBusinessRepository.findByName(name);

        if(dbClassOfBusiness.isEmpty()) return "No such class of business";

        if (classOfBusinessRepository.deleteByName(name) == 1) return name;

        return "Delete failed";

    }
}
