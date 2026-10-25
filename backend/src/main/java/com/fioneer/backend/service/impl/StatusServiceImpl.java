package com.fioneer.backend.service.impl;

import com.fioneer.backend.entity.Status;
import com.fioneer.backend.repository.StatusRepository;
import com.fioneer.backend.service.StatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public String getStatus(String status) {

        Optional<Status> dbStatus = statusRepository.findByStatus(status);

        if(dbStatus.isEmpty()) return "No such status";

        return dbStatus.get().getStatus();
    }

    @Override
    public String createStatus(String status) {
        Optional<Status> dbStatus = statusRepository.findByStatus(status);

        if(dbStatus.isPresent()) return "status already exists";

        Status savedStatus = statusRepository.saveAndFlush(new Status(status));

        return savedStatus.getStatus();

    }

    @Override
    public String updateStatus(String oldStatus, String newStatus) {

        Optional<Status> dbStatus = statusRepository.findByStatus(oldStatus);

        if(dbStatus.isEmpty()) return "No such status";

        Status dbStatusTemp = dbStatus.get();

        dbStatusTemp.setStatus(newStatus);

        return statusRepository.saveAndFlush(dbStatusTemp).getStatus();

    }

    @Override
    @Transactional
    public String deleteStatus(String status) {

        Optional<Status> dbStatus = statusRepository.findByStatus(status);

        if(dbStatus.isEmpty()) return "No such status";

        if (statusRepository.deleteByStatus(status) == 1) return status;

        return "Delete failed";

    }
}
